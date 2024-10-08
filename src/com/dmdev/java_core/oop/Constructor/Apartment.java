package com.dmdev.java_core.oop.Constructor;

public class Apartment {
    private final Integer numberOfApartment;   // номер квартиры
    private final Room[] rooms;  // массив комнат

    public Apartment(int numberOfApartment, Room[] rooms) {
        this.numberOfApartment = numberOfApartment;
        this.rooms = rooms;
    }

    public int getNumberOfApartment() {
        return numberOfApartment;
    }

    public Room[] getRooms() {
        return rooms;
    }

    public void print() {
        System.out.println("Номер квартиры: " + getNumberOfApartment() + ", Количество комнат: " + getRooms().length);
    }
}
