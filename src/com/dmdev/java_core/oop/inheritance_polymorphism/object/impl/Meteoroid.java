package com.dmdev.java_core.oop.inheritance_polymorphism.object.impl;

import com.dmdev.java_core.oop.inheritance_polymorphism.object.AbstractCosmicObject;
import com.dmdev.java_core.oop.inheritance_polymorphism.enums.ConsistOfMeteoroid;
import com.dmdev.java_core.oop.inheritance_polymorphism.object.Rotating;

public class Meteoroid extends AbstractCosmicObject implements Rotating {

    private ConsistOfMeteoroid consist;
    private Boolean isMeteorites;

    public Meteoroid(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, ConsistOfMeteoroid consist, Boolean isMeteorites) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.consist = consist;
        this.isMeteorites = isMeteorites;
    }

    public void calculateSpeedEntryInAtmospherePlanet(Integer high, Integer temperature) {
        if (this.isMeteorites = true) {
            Double speed = Math.sqrt(2 * high * temperature);
            System.out.println(getName() + " входит в атмосферу планеты со скоростью " + speed + " и становится метеоритом");
        } else {
            System.out.println(getName() + " не является метеоритом");
        }
    }

    @Override
    public void printInformation() {
        super.printInformation();
        switch (this.consist) {
            case ROCKY_METEOROID -> System.out.print(". Состоит из камеснистых материалов");
            case METAL -> System.out.print(". Состоит из металлов: железа и никеля");
            case MIXTURE -> System.out.print(". Состоит из смеси камня и металлов");
        }
    }

    @Override
    public void rotate(Double speed) {
        System.out.print(". " + getName() + " вращается вокруг солнца со скоростью " + speed + ". ");
    }

    @Override
    public String toString() {
        return "Meteoroid{" +
                "consist='" + consist + '\'' +
                ", isMeteorites=" + isMeteorites +
                '}';
    }

    public ConsistOfMeteoroid getConsist() {
        return consist;
    }

    public Boolean getMeteorites() {
        return isMeteorites;
    }
}
