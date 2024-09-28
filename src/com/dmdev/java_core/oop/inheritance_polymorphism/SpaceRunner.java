package com.dmdev.java_core.oop.inheritance_polymorphism;

import static com.dmdev.java_core.oop.inheritance_polymorphism.ConsistOfMeteoroid.ROCKY_METEOROID;
import static com.dmdev.java_core.oop.inheritance_polymorphism.ConsistOfPlanet.ROCKY;
import static com.dmdev.java_core.oop.inheritance_polymorphism.TypeOfPlanet.TERRESTRIAL_PLANETS;
import static com.dmdev.java_core.oop.inheritance_polymorphism.TypeOfStar.BLUE;

public class SpaceRunner {
    public static void main(String[] args) {
        CosmicObject asteroid = new Asteroid("Астероид", "Церера", 1.9,
                34, 123.9, TypesOfAsteroids.C_TYPE, 123.9);
        CosmicObject blackHole = new BlackHole("Черная дыра", "Керра", 456.09,
                567, 478.0, TypeOfBlackHoles.STELLAR_BLACK_HOLE, false);
        CosmicObject comet = new Comet("Астероид", "Галлея", 17.928,
                162, 3045.9, 34.65, true);
        CosmicObject meteoroid = new Meteoroid("Метеороид", "Хоба", 58.12,
                123, 4000.9, ROCKY_METEOROID, true);
        CosmicObject planet = new Planet("Планета", "Земля", 9722.06 * 1024, 510072,
                510072.87, 1, ROCKY, true, TERRESTRIAL_PLANETS);
        CosmicObject satellite = new Satellite("Спутник", "Луна", 345.5, 5464,
                678432.02, 29.5, 27.53, true, 384467.001);
        CosmicObject star = new Star("Звезда", "Сириус", 5645.987, 7009,
                199485.94, 'A', 50, BLUE);

        asteroid.printInformation();
        asteroid.rotate(123.5);
        asteroid.calculatingDiameter();

        blackHole.printInformation();
        blackHole.rotate(123.5);
        blackHole.calculatingDiameter();

        comet.printInformation();
        comet.rotate(123.5);
        comet.calculatingDiameter();

        meteoroid.printInformation();
        meteoroid.rotate(123.5);
        meteoroid.calculatingDiameter();

        planet.printInformation();
        planet.rotate(123.5);
        planet.calculatingDiameter();

        satellite.printInformation();
        satellite.rotate(123.5);
        satellite.calculatingDiameter();

        star.printInformation();
        star.rotate(123.5);
        star.calculatingDiameter();

        comet.compareWeight(planet);

        SpaceUtils.calculateForceOfGravity(asteroid, blackHole);
        SpaceUtils.calculateForceOfGravity(planet, satellite);

        SpaceUtils.isStar(asteroid);
        SpaceUtils.isStar(star);
    }
}
