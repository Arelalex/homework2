package com.dmdev.java_core.oop.inheritance_polymorphism.object.impl;

import com.dmdev.java_core.oop.inheritance_polymorphism.object.AbstractCosmicObject;

public class Comet extends AbstractCosmicObject implements Rotating {

    private Double coreSize;
    private Boolean cometTail;

    public Comet(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, Double coreSize, Boolean cometTail) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.coreSize = coreSize;
        this.cometTail = cometTail;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        System.out.print(". Размер ядра: " + getCoreSize() + ". ");
        if (this.cometTail) {
            System.out.print(getName() + " имеет ионный или пылевой хвост");
        } else {
            System.out.print(getName() + " не имеет хвост");
        }
    }

    @Override
    public void rotate(Double speed) {
        System.out.print(". " + getName() + " вращается вокруг своей оси со скоростью " + speed + ". ");
    }

    public Double getCoreSize() {
        return coreSize;
    }

    public Boolean getCometTail() {
        return cometTail;
    }

    @Override
    public String toString() {
        return "Comet{" +
                "coreSize=" + coreSize +
                ", cometTail=" + cometTail +
                '}';
    }
}
