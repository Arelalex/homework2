package com.dmdev.java_core.oop.inheritance_polymorphism;

public class Satellite extends CosmicObject implements Rotating {

    private Double orbitalPeriod;
    private Double timeOfCompleteRevolution;
    private Boolean hasAtmosphere;
    private Double distanceFromPlanet;

    public Satellite(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, Double orbitalPeriod, Double timeOfCompleteRevolution, Boolean hasAtmosphere, Double distanceFromPlanet) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.orbitalPeriod = orbitalPeriod;
        this.timeOfCompleteRevolution = timeOfCompleteRevolution;
        this.hasAtmosphere = hasAtmosphere;
        this.distanceFromPlanet = distanceFromPlanet;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        if (this.hasAtmosphere) {
            System.out.print(". Спутник имеет атмосферу");
        } else {
            System.out.print(". Спутник не имеет атмосферу");
        }
    }

    @Override
    public void rotate(Double speed) {
        System.out.print(". " + getName() + " вращается вокруг своей оси со скоростью " + speed + ". ");
    }

    @Override
    public String toString() {
        return "Satellite{" +
                "orbitalPeriod=" + orbitalPeriod +
                ", timeOfCompleteRevolution=" + timeOfCompleteRevolution +
                ", hasAtmosphere=" + hasAtmosphere +
                ", distanceFromPlanet=" + distanceFromPlanet +
                '}';
    }

    public Double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Double getTimeOfCompleteRevolution() {
        return timeOfCompleteRevolution;
    }

    public Boolean getHasAtmosphere() {
        return hasAtmosphere;
    }

    public Double getDistanceFromPlanet() {
        return distanceFromPlanet;
    }
}
