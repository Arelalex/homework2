package com.dmdev.thread_new;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Race {
    private final Lock lock = new ReentrantLock();
    private final String name;
    private int totalRedCrystals = 0;
    private int totalWhiteCrystals = 0;

    public Race(String name) {
        this.name = name;
    }

    public void addCrystal(ConcurrentHashMap<CrystalType, Integer> crystalFromRocket) {
        lock.lock();
        try {
            totalRedCrystals += crystalFromRocket.get(CrystalType.RED);
            totalWhiteCrystals += crystalFromRocket.get(CrystalType.WHITE);
        } finally {
            lock.unlock();
        }
    }

    public int getTotalRedCrystals() {
        return totalRedCrystals;
    }

    public int getTotalWhiteCrystals() {
        return totalWhiteCrystals;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Race{" +
                "name='" + name + '\'' +
                '}';
    }
}

