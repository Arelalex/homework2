package com.dmdev.regex.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Client implements Runnable {
    private static final Random RANDOM = new Random();
    private static long number = 0;
    private static final int period = 10;
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    List<String> firstNameLastName = new ArrayList<>(List.of(
            "Светикова Света",
            "Сидоров Сидор",
            "Кузьмин Георгий",
            "Коновалова Аврора",
            "Лапина Кира",
            "Андреев Давид"));
    List<String> numbersOfPhone = new ArrayList<>(List.of(
            "375 33 1233256",
            "335643278",
            "+375 (29) 444 56 33",
            "29 1234567",
            "25 1111122",
            "375298871290"));
    List<String> complaints = new ArrayList<>(List.of(
            "Не включается свет",
            "Почему опять не работает интернет?",
            "Кто-то оборвал мне телефонный кабель",
            "Заклинило дверь подъезда",
            "Во дворе сломана лавочка",
            "Застрял лифт",
            "Перегорела лампочка на 4 этаже"));

    @Override
    public void run() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                writeInFileComplaints();
            } catch (Exception e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        }, 0, period, TimeUnit.SECONDS);
    }

    public List<String> doRandomClient() {
        List<String> entries = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Path customerComplaints = Path.of("resources", "complaints.csv");
        try {
            long lineCountInComplaintsFile = Files.lines(customerComplaints).count();
            System.out.println("Количество строк в файле" + lineCountInComplaintsFile);

            if (lineCountInComplaintsFile == 0) {
                number++;
                String numberOfClient = String.valueOf(number);
                String date = LocalDateTime.now().plusDays(RANDOM.nextInt(30)).format(formatter);
                String user = firstNameLastName.get(RANDOM.nextInt(firstNameLastName.size()));
                String phone = numbersOfPhone.get(RANDOM.nextInt(numbersOfPhone.size()));
                String complaint = complaints.get(RANDOM.nextInt(complaints.size()));
                String entry = String.join(", ", numberOfClient, date, user, phone, complaint);
                entries.add(entry);
                System.out.println("Появилась новая жалоба, номер " + numberOfClient);

            } else {
                number = lineCountInComplaintsFile + 1;
                String numberOfClient = String.valueOf(number);
                String date = LocalDateTime.now().plusDays(RANDOM.nextInt(30)).format(formatter);
                String user = firstNameLastName.get(RANDOM.nextInt(firstNameLastName.size()));
                String phone = numbersOfPhone.get(RANDOM.nextInt(numbersOfPhone.size()));
                String complaint = complaints.get(RANDOM.nextInt(complaints.size()));
                String entry = String.join(", ", numberOfClient, date, user, phone, complaint);
                entries.add(entry);
                System.out.println("Появилась новая жалоба, номер " + numberOfClient);
            }
            return entries;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeInFileComplaints() {
        Path customerComplaints = Path.of("resources", "complaints.csv");
        try {
            boolean fileNotEmpty = Files.exists(customerComplaints) && Files.size(customerComplaints) > 0;
            int randomCountComplaints = ThreadLocalRandom.current().nextInt(1, 4);
            for (int i = 0; i < randomCountComplaints; i++) {
                List<String> randomEntries = doRandomClient();

                if (fileNotEmpty && randomEntries.isEmpty()) {
                    randomEntries.set(0, System.lineSeparator() + randomEntries.get(0));
                }
                Files.write(customerComplaints, randomEntries, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл " + customerComplaints);
            throw new RuntimeException(e);
        }
    }

    public void stopWriting() {
        System.out.println("Остановка записи жалоб в файл...");
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(1, TimeUnit.MINUTES)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
