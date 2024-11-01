package com.dmdev.regex;

import com.dmdev.regex.client.Client;
import com.dmdev.regex.dispather.ComplaintProcessing;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.*;

public class RunnerRegex {
    public static void main(String[] args) throws IOException {

        Path customerComplaints = Path.of("resources", "complaints.csv");

        Client clients = new Client();
        ComplaintProcessing fileReading = new ComplaintProcessing(customerComplaints);
        int randomCountComplaints = ThreadLocalRandom.current().nextInt(1, 4);

        ExecutorService executor = Executors.newFixedThreadPool(randomCountComplaints);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        executor.submit(clients);
        executor.submit(fileReading);

        scheduler.schedule(() -> {
            try {
                executor.shutdown();
                fileReading.shutdown();
                clients.stopWriting();

                if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                    executor.shutdownNow();
                }
                System.out.println("Задачи завершены");
            } catch (InterruptedException e) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            } finally {
                scheduler.shutdown();
            }
        }, 1, TimeUnit.MINUTES);
    }
}









