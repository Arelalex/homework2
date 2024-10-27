package com.dmdev.thread_new;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Rocket implements Runnable {

    private static final Semaphore semaphore = new Semaphore(1);
    private final Race race;
    private final Planet planet;

    public Rocket(Race race, Planet planet) {
        this.race = race;
        this.planet = planet;
    }

    @Override
    public void run() {
        while (race.getTotalWhiteCrystals() < 50 || race.getTotalRedCrystals() < 50) {
            ConcurrentHashMap<CrystalType, Integer> collectedCrystals = null;
            try {
                collectedCrystals = loadCrystals();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (collectedCrystals != null) {
                try {
                    loadCrystals();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Сбор кристаллов завершен.");
    }

    public ConcurrentHashMap<CrystalType, Integer> loadCrystals() throws InterruptedException {
        ConcurrentHashMap<CrystalType, Integer> crystalFromRocket = new ConcurrentHashMap<>();
        crystalFromRocket.put(CrystalType.RED, 0);
        crystalFromRocket.put(CrystalType.WHITE, 0);

        semaphore.acquire();

        try {
            if (planet.hasCrystals() >= 2) {
                int crystalsCount = 0;
                if (planet.hasCrystals() >= 6) {
                    crystalsCount = ThreadLocalRandom.current().nextInt(2, 6);
                }
                if (planet.hasCrystals() < 6) {
                    crystalsCount = ThreadLocalRandom.current().nextInt(2, planet.hasCrystals() + 1);
                }

                for (int i = 0; i < crystalsCount; i++) {
                    CrystalType type = ThreadLocalRandom.current().nextBoolean() ? CrystalType.RED : CrystalType.WHITE;
                    if (Planet.areAvailableCrystals(type).equals(CrystalType.RED)) {
                        crystalFromRocket.compute(CrystalType.RED, (k, v) -> v + 1);
                    } else {
                        crystalFromRocket.compute(CrystalType.WHITE, (k, v) -> v + 1);
                    }
                }
                System.out.println(race.getName() + " Ракета собрала " + crystalFromRocket.get(CrystalType.RED) +
                        " красных кристаллов и " + crystalFromRocket.get(CrystalType.WHITE) + " белых кристаллов");

            } else {
                System.out.println(race.getName() + " -------- Не достаточно кристаллов для сбора, ракета улетает пустой");
            }
            return crystalFromRocket;

        } finally {
            semaphore.release();
        }
    }

}
