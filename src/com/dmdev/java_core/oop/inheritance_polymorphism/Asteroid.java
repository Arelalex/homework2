package com.dmdev.java_core.oop.inheritance_polymorphism;

public class Asteroid extends CosmicObject implements Rotating {
    private TypesOfAsteroids consist;
    private Double rotationSpeed;

    public Asteroid(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, TypesOfAsteroids consist, Double rotationSpeed) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.consist = consist;
        this.rotationSpeed = rotationSpeed;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        switch (this.consist) {
            case C_TYPE -> System.out.print(". Состоит из углеродных соединений");
            case S_TYPE -> System.out.print(". Состоит из из силикатов и камней");
            case M_TYPE -> System.out.print(". Состоит в основном из металлов, таких как железо и никель");
        }
    }

    @Override
    public void rotate(Double rotationSpeed) {
        System.out.print(". " + getName() + " вращается вокруг своей оси со скоростью: " + getRotationSpeed() + ". ");
    }

    @Override
    public String toString() {
        return "Asteroid{" +
                "consist=" + consist +
                ", rotationSpeed=" + rotationSpeed +
                '}';
    }

    public TypesOfAsteroids getConsist() {
        return consist;
    }

    public Double getRotationSpeed() {
        return rotationSpeed;
    }
}

