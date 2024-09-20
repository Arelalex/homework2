package com.dmdev.java_core.oop.Constructor;

public class Build {

    private int numberOfBuild;   // номер дома
    private Floor[] floor;   // массив этажей

    public Build(int numberOfBuild, Floor[] floors) {
        this.numberOfBuild = numberOfBuild;
        this.floor = floors;
    }

    public int getNumberOfBuild() {
        return numberOfBuild;
    }

    public Floor[] getFloor() {
        return floor;
    }

    public void print() {
        System.out.println("Номер дома: " + getNumberOfBuild() + ", Этаж: " + getFloor().length);
    }
}
