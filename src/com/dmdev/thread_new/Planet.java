package com.dmdev.thread_new;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Planet implements Runnable {
    private static final Lock lock = new ReentrantLock();
    private static AtomicInteger redCrystals = new AtomicInteger(0);
    private static AtomicInteger whiteCrystals = new AtomicInteger(0);

    public Planet() {
    }

    public static CrystalType areAvailableCrystals(CrystalType crystalType) {
        lock.lock();
        try {
            if (crystalType.equals(CrystalType.RED)) {
                if (redCrystals.get() != 0) {
                    synchronized (Planet.class) {
                        redCrystals.getAndDecrement();
                    }
                    return CrystalType.RED;
                } else {
                    whiteCrystals.getAndDecrement();
                    synchronized (Planet.class) {
                        return CrystalType.WHITE;
                    }
                }
            } else {
                if (whiteCrystals.get() != 0) {
                    synchronized (Planet.class) {
                        whiteCrystals.getAndDecrement();
                    }
                    return CrystalType.WHITE;
                } else {
                    synchronized (Planet.class) {
                        redCrystals.getAndDecrement();
                    }
                    return CrystalType.RED;
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static AtomicInteger getRedCrystals() {
        return redCrystals;
    }

    public static void setRedCrystals(AtomicInteger redCrystals) {
        Planet.redCrystals = redCrystals;
    }

    public static AtomicInteger getWhiteCrystals() {
        return whiteCrystals;
    }

    public static void setWhiteCrystals(AtomicInteger whiteCrystals) {
        Planet.whiteCrystals = whiteCrystals;
    }

    @Override
    public void run() {
        growCrystals();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Рост кристаллов на планете завершен.");
    }

    public void growCrystals() {
        lock.lock();
        try {
            int totalCrystalsToGrow = ThreadLocalRandom.current().nextInt(2, 6);
            int redCrystalToGrow = ThreadLocalRandom.current().nextInt(0, totalCrystalsToGrow + 1);
            int whiteCrystalToGrow = totalCrystalsToGrow - redCrystalToGrow;
            redCrystals.addAndGet(redCrystalToGrow);
            whiteCrystals.addAndGet(whiteCrystalToGrow);

            System.out.println();
            System.out.println("Планета вырастила " + redCrystalToGrow + " красных кристаллов и " + whiteCrystalToGrow + " белых кристаллов");

        } finally {
            lock.unlock();
        }
        try {
            Thread.sleep(1000); // планета выращивает кристаллы раз в сутки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Рост кристаллов был прерван");
        }
    }

    public int hasCrystals() {
        lock.lock();
        try {
            return redCrystals.get() + whiteCrystals.get();
        } finally {
            lock.unlock();
        }
    }
}
