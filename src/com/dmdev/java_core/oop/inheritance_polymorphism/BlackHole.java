package com.dmdev.java_core.oop.inheritance_polymorphism;

public class BlackHole extends CosmicObject implements Rotating {
    private TypeOfBlackHoles type;
    private Boolean spin;

    public BlackHole(String nameOfCosmicObject, String name, Double weight, Integer size, Double distanceFromSun, TypeOfBlackHoles type, Boolean spin) {
        super(nameOfCosmicObject, name, weight, size, distanceFromSun);
        this.type = type;
        this.spin = spin;
    }

    @Override
    public void printInformation() {
        super.printInformation();
        switch (this.type) {
            case STELLAR_BLACK_HOLE -> System.out.print(". Масса от 3 до 20 масс Солнца");
            case SUPERMASSIVE_BLACK_HOLE -> System.out.print(". Масса от миллионов до миллиардов масс Солнца");
            case INTERMEDIATE_BLACK_HOLE ->
                    System.out.print(". Масса от нескольких сотен до нескольких тысяч масс Солнца");
            case MICRO_BLACK_HOLE -> System.out.print(". Масса меньше чем масса звезды");
        }
    }

    @Override
    public void rotate(Double speed) {
        if (this.spin) {
            System.out.println(". " + getName() + " вращается вокруг своей оси со скоростью " + speed + ". ");
        } else {
            System.out.print(". " + getName() + " не может вращаться вокруг своей оси. ");
        }
    }

    @Override
    public String toString() {
        return "BlackHole{" +
                "type='" + type + '\'' +
                ", spin=" + spin +
                '}';
    }

    public TypeOfBlackHoles getType() {
        return type;
    }

    public Boolean getSpin() {
        return spin;
    }
}
