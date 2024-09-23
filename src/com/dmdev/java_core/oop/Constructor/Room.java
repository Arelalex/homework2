package com.dmdev.java_core.oop.Constructor;

public class Room {
    private final Boolean throughRoom;

    public Room(boolean throughRoom) {
        this.throughRoom = throughRoom;
    }

    public boolean isThroughRoom() {
        return throughRoom;
    }

    public void print() {
        System.out.println("Проходная комната: " + isThroughRoom());
    }
}
