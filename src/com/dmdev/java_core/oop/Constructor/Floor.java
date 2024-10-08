package com.dmdev.java_core.oop.Constructor;

public class Floor {
    private final Integer numberOfFloor;  // номер этажа
    private final Apartment[] apartment;    // массив квартир

    public Floor(int numberOfFloor, Apartment[] apartment) {
        this.numberOfFloor = numberOfFloor;
        this.apartment = apartment;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public Apartment[] getApartment() {
        return apartment;
    }

    public void print(){
        System.out.println("Номер этажа: "+ getNumberOfFloor() + ", Количество квартир: " + getApartment().length);
    }
}
