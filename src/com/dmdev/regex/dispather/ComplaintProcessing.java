package com.dmdev.regex.dispather;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ComplaintProcessing implements Runnable {

    private static final Random RANDOM = new Random();
    private static final Path customerComplaints = Path.of("resources", "complaints.csv");
    private static final Path complaintsProcessed = Path.of("resources", "processed.csv");
    private static final Path positionFilePath = Path.of("resources", "lastPosition.dat");
    private final Path logFile;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final int period = 15;
    private final int randomCountDispatcher = ThreadLocalRandom.current().nextInt(2, 4);
    private final ExecutorService dispatcherPool = Executors.newFixedThreadPool(randomCountDispatcher); // Ограничиваем количество диспетчеров
    private long lastReadPosition = 0;

    public ComplaintProcessing(Path logFile) {
        this.logFile = logFile;
    }

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                List<String> newEntries = readNewEntries();
                if (!newEntries.isEmpty()) {
                    dispatchEntries(newEntries);
                }
            } catch (IOException e) {
                System.err.println("Ошибка при чтении файла: " + e.getMessage());
            }
        }, 0, period, TimeUnit.SECONDS);
    }

    public void saveLastReadPosition() {
        try {
            Files.writeString(positionFilePath, String.valueOf(lastReadPosition), StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении позиции чтения: " + e.getMessage());
        }
    }

    public void loadLastReadPosition() {
        if (Files.exists(positionFilePath)) {
            try {
                String positionStr = Files.readString(positionFilePath);
                lastReadPosition = Long.parseLong(positionStr);
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка при загрузке позиции чтения: " + e.getMessage());
                lastReadPosition = 0;
            }
        }
    }

    public List<String> readNewEntries() throws IOException {
        loadLastReadPosition();
        List<String> newEntries = new ArrayList<>();
        try (SeekableByteChannel reader = Files.newByteChannel(customerComplaints, StandardOpenOption.READ)) {
            reader.position(lastReadPosition);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            StringBuilder newLines = new StringBuilder();

            while (reader.read(buffer) > 0) {
                buffer.flip();
                newLines.append(StandardCharsets.UTF_8.decode(buffer));
                buffer.clear();
            }

            String[] lines = newLines.toString().split("\n");
            for (String line : lines) {
                if (!line.isEmpty()) {
                    newEntries.add(line);
                }
            }
            lastReadPosition = reader.position();
        }
        saveLastReadPosition();
        return newEntries;
    }

    private void dispatchEntries(List<String> entries) {
        for (String entry : entries) {
            dispatcherPool.submit(() -> callsClient(entry));
        }
    }

    private List<LogFile> callsClient(String entry) {
        System.out.println("Диспетчер обрабатывает запись: " + entry);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        System.out.println("Заявка обработана " + entry);

        String newEntry = entry;
        List<LogFile> log = Arrays.stream(newEntry.split("\n"))
                .map(line -> {
                    String[] values = line.split(",");
                    LogFile logFile = new LogFile();
                    logFile.setNumber(values[0]);

                    int durationSeconds = 3 + RANDOM.nextInt(3);
                    int durationMinutes = 1 + RANDOM.nextInt(2);

                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String formattedDateTime = currentDateTime.format(formatter);

                    String duration = String.format("%02d:%02d", durationMinutes, durationSeconds);
                    String dateAndTime = formattedDateTime + " " + duration;
                    logFile.setDateTime(dateAndTime);

                    String formattedPhone = formatPhoneNumber(values[3]);
                    logFile.setNumberPhone(formattedPhone);

                    return logFile;
                })
                .collect(Collectors.toList());
        writeInFile(log);
        return log;
    }

    private String formatPhoneNumber(String phone) {
        String digits = phone.replaceAll("\\D", "");

        if (digits.length() == 9) {
            Pattern pattern = Pattern.compile("(\\d{2})(\\d{3})(\\d{2})(\\d{2})");
            Matcher matcher = pattern.matcher(digits);
            if (matcher.matches()) {
                return "+375 (" + matcher.group(1) + ") " + matcher.group(2) + "-" + matcher.group(3) + "-" + matcher.group(4);
            }
        } else if (digits.length() == 12 && digits.startsWith("375")) {
            Pattern pattern = Pattern.compile("(375)(\\d{2})(\\d{3})(\\d{2})(\\d{2})");
            Matcher matcher = pattern.matcher(digits);
            if (matcher.matches()) {
                return "+" + matcher.group(1) + " (" + matcher.group(2) + ") " + matcher.group(3) + "-" + matcher.group(4) + "-" + matcher.group(5);
            }
        }
        System.out.println("Неверный формат номера: " + phone);
        return phone;
    }

    public void writeInFile(List<LogFile> log) {
        try {
            Files.write(complaintsProcessed, log.stream()
                            .map(LogFile::writeToLogFile)
                            .collect(Collectors.toList()),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Логи записаны в файл " + log);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл: " + e.getMessage(), e);
        }
    }

    public void shutdown() {
        scheduler.shutdown();
        dispatcherPool.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                scheduler.shutdownNow();
            }
            if (!dispatcherPool.awaitTermination(1, TimeUnit.MINUTES)) {
                dispatcherPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            dispatcherPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
