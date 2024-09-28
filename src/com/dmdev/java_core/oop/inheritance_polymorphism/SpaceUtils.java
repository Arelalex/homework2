package com.dmdev.java_core.oop.inheritance_polymorphism;

public final class SpaceUtils {
    private final static Double GRAVITATIONAL_CONSTANT = 6.674 * Math.pow(10, -11);
    private final static Double MULTIPLIER_FOR_MASS = Math.pow(10, 20);
    private final static Double MULTIPLIER_FOR_RADIUS = Math.pow(10, 7);

    private SpaceUtils() {
    }

    public static Double calculateForceOfGravity(CosmicObject objectOne, CosmicObject objectTwo) {
        double diameterOne = objectOne.calculatingDiameter();
        double radiusOne = diameterOne / 2;
        double forceOfGravity = GRAVITATIONAL_CONSTANT * ((objectOne.getWeight() * MULTIPLIER_FOR_MASS) * objectTwo.getWeight())
                / (radiusOne * MULTIPLIER_FOR_RADIUS);
        System.out.println(objectOne.getName() + " и " + objectTwo.getName() + ". Сила гравитации равна " + forceOfGravity);
        return forceOfGravity;
    }

    public static Boolean isStar(CosmicObject object) {
        if (object instanceof Star) {
            System.out.println(object.getName() + " является звездой");
            return true;
        }
        System.out.println(object.getName() + " не является звездой");
        return false;
    }
}
