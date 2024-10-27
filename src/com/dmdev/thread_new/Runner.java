package com.dmdev.thread_new;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class Runner {
    public static void main(String[] args) throws InterruptedException {

        Planet planet = new Planet();

        Race magesFire = new Race("Маги огня");
        Race magesAir = new Race("Маги воздуха");

        Rocket rocketOfFireMages = new Rocket(magesFire, planet);
        Rocket rocketOfAirMages = new Rocket(magesAir, planet);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            if (
                    magesFire.getTotalRedCrystals() >= 50 || magesAir.getTotalWhiteCrystals() >= 50) {
                System.out.println("Сбор кристаллов завершен. Остановлен сбор.");
                if (magesFire.getTotalRedCrystals() >= 50) {
                    System.out.println("Маги огня победили, собрав " + magesFire.getTotalRedCrystals() + " красных кристаллов");
                } else if (magesAir.getTotalWhiteCrystals() >= 50) {
                    System.out.println("Маги воздуха победили, собрав " + magesAir.getTotalWhiteCrystals() + " белых кристаллов");
                }
                scheduler.shutdown();
                return;
            }

            planet.growCrystals();

            boolean chooseFireMages = ThreadLocalRandom.current().nextBoolean();
            try {
                if (chooseFireMages) {
                    collectAndDisplayCrystals(rocketOfFireMages, magesFire);
                    collectAndDisplayCrystals(rocketOfAirMages, magesAir);
                } else {
                    collectAndDisplayCrystals(rocketOfAirMages, magesAir);
                    collectAndDisplayCrystals(rocketOfFireMages, magesFire);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 500, MILLISECONDS);

        try {
            if (!scheduler.awaitTermination(5, TimeUnit.MINUTES)) {
                System.out.println("Не удалось завершить работу в течение 1 минуты. Принудительное завершение...");
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            scheduler.shutdownNow();
            scheduler.awaitTermination(1L, MILLISECONDS);
        }
    }

    private static void collectAndDisplayCrystals(Rocket rocket, Race mages) {
        try {
            ConcurrentHashMap<CrystalType, Integer> crystals = rocket.loadCrystals();
            mages.addCrystal(crystals);
            System.out.println(mages.getName() + " - общее количество красных кристаллов: " + mages.getTotalRedCrystals() +
                    " и общее количество белых кристаллов: " + mages.getTotalWhiteCrystals());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}










