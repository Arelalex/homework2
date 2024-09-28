package com.dmdev.java_core.oop.inheritance_polymorphism;

public class Planet extends CosmicObject implements Rotating {
    private Integer numberOfSatellite;
    private ConsistOfPlanet additionalConsist;
    private Boolean hasLife;
    private TypeOfPlanet typeOfPlanet;

    public Planet(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, Integer numberOfSatellite, ConsistOfPlanet additionalConsist, Boolean hasLife, TypeOfPlanet typeOfPlanet) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.numberOfSatellite = numberOfSatellite;
        this.additionalConsist = additionalConsist;
        this.hasLife = hasLife;
        this.typeOfPlanet = typeOfPlanet;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        switch (this.typeOfPlanet) {
            case TERRESTRIAL_PLANETS -> System.out.print(". " + getName() + " относится к планетам земной группы");
            case GAS_GIANTS -> System.out.print(". " + getName() + " относится к Газовым гигантам");
            case ICE_GIANTS -> System.out.print(". " + getName() + " относится к Ледяным гигантам");
            case DWARF_GIANTS -> System.out.print(". " + getName() + " относится к Карликовым планетам");
        }
        switch (this.additionalConsist) {
            case ROCKY -> System.out.print(". Каменистая планета - состоит из силикатов и металлов. ");
            case GAS_GIANTS -> System.out.print(". Газовый гигант - состоит из водорода и гелия. ");
            case ICE_GIANTS -> System.out.print(". Ледяной гигант - состоит из водяного льда, аммиака и метана. ");
        }
        if (this.hasLife) {
            System.out.print(" Планета имеет жизнь");
        } else {
            System.out.print(" Планета не имеет жизнь");
        }
    }

    @Override
    public void rotate(Double speed) {
        System.out.print(". " + getName() + " вращается вокруг солнца со скоростью " + speed + ". ");
    }

    @Override
    public String toString() {
        return "Planet{" +
                ", numberOfSatellite=" + numberOfSatellite +
                ", additionalConsist='" + additionalConsist + '\'' +
                ", hasLife=" + hasLife +
                ", typeOfPlanet=" + typeOfPlanet +
                '}';
    }

    public Integer getNumberOfSatellite() {
        return numberOfSatellite;
    }

    public ConsistOfPlanet getAdditionalConsist() {
        return additionalConsist;
    }

    public Boolean getHasLife() {
        return hasLife;
    }

    public TypeOfPlanet getTypeOfPlanet() {
        return typeOfPlanet;
    }
}
