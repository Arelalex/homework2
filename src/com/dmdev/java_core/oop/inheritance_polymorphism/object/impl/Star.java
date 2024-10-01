package com.dmdev.java_core.oop.inheritance_polymorphism.object.impl;

import com.dmdev.java_core.oop.inheritance_polymorphism.object.AbstractCosmicObject;
import com.dmdev.java_core.oop.inheritance_polymorphism.enums.TypeOfStar;
import com.dmdev.java_core.oop.inheritance_polymorphism.object.Rotating;

public class Star extends AbstractCosmicObject implements Rotating {
    private Character spectralClass;
    private Integer ageStar;
    private TypeOfStar typeOfStar;

    public Star(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, Character spectralClass, Integer ageStar, TypeOfStar typeOfStar) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.spectralClass = spectralClass;
        this.ageStar = ageStar;
        this.typeOfStar = typeOfStar;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        switch (this.typeOfStar) {
            case BLUE ->
                    System.out.print(". " + getName() + " относится к синим звездам, с температурой выше 30,000 К");
            case YELLOW ->
                    System.out.print(". " + getName() + " относится к желтым звездам и имеет температуру около 5,500 К");
            case RED ->
                    System.out.print(". " + getName() + " относится к красным звездам, более холодная, с температурой около 3,000 К");
        }
    }

    @Override
    public void rotate(Double speed) {
        System.out.print(". " + getName() + " вращается вокруг своей оси со скоростью " + speed + ". ");
    }

    @Override
    public String toString() {
        return "Star{" +
                "spectralClass=" + spectralClass +
                ", ageStar=" + ageStar +
                ", typeOfStar=" + typeOfStar +
                '}';
    }

    public Character getSpectralClass() {
        return spectralClass;
    }

    public Integer getAgeStar() {
        return ageStar;
    }

    public TypeOfStar getTypeOfStar() {
        return typeOfStar;
    }
}
