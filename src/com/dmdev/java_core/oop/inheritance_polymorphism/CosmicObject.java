package com.dmdev.java_core.oop.inheritance_polymorphism;

public abstract class CosmicObject implements Comparable, Rotating, CalculatingDiameter {

    private String nameOfCosmicObject;
    private String name;
    private Double weight;
    private Integer size;
    private Double distanceFromSun;

    public CosmicObject(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun) {
        this.nameOfCosmicObject = nameOfCosmicObject;
        this.name = name;
        this.weight = weight;
        this.size = size;
        this.distanceFromSun = distanceFromSun;
    }

    @Override
    public void compareWeight(CosmicObject object) {
        if (this.weight > object.weight) {
            System.out.println("Масса " + this.name + " больше, чем " + object.name);
        } else if (object.weight > this.weight) {
            System.out.println("Масса " + object.name + " больше, чем " + this.name);
        } else {
            System.out.println("Массы равны");
        }
    }

    public void printInformation() {
        System.out.print("Название космического тела: " + getNameOfCosmicObject() + ", Имя: " + getName() + ", Масса: " + getWeight() +
                ", Размер: " + getSize() + ", Расстояние от солнца: " + getDistanceFromSun());
    }

    @Override
    public Double calculatingDiameter() {
        double diameter;
        diameter = 2 * Math.sqrt(this.size / (4 * 3.14));
        System.out.println(getName() + " имеет диаметр: " + diameter + ". ");
        return diameter;
    }

    public String getName() {
        return name;
    }

    public Double getWeight() {
        return weight;
    }

    public Integer getSize() {
        return size;
    }

    public Double getDistanceFromSun() {
        return distanceFromSun;
    }

    public String getNameOfCosmicObject() {
        return nameOfCosmicObject;
    }
}
