package com.dmdev.java_core.oop.Constructor;

/**
 * Представить в виде классов и их композиции следующую модель.
 * - Каждый дом содержит свой номер (int), и множество этажей (массив).
 * - Каждый этаж содержит номер этажа (int), и множество квартир (массив).
 * - Каждая квартира содержит свой номер (int), и множество комнат (массив).
 * - Каждая комната содержит поле проходная она или нет (boolean).
 * <p>
 * В каждом классе реализовать метод print, который на консоль выводит информацию об объекте (должны присутствовать все поля объекта!).
 * Например, метод print класса этаж должен выводить на консоль:
 * “Этаж 2, количество квартир 18”
 * <p>
 * Создание всех объектов вынести в отдельный класс с методом main.
 * Там же реализовать метод printAllInformation, который на вход принимает объект типа дом, и выводит информацию о нем, его этажах, квартирах и комнатах, вызывая методы print.
 */

public class ModelOfBuild {
    public static void main(String[] args) {

        Room throughRoom = new Room(true);
        Room notThroughRoom = new Room(false);

        Room[] oneRoom = {throughRoom};
        Room[] twoRooms = {throughRoom, notThroughRoom};
        Room[] threeRooms = {throughRoom, notThroughRoom, notThroughRoom};

        Apartment firstApartment = new Apartment(1, oneRoom);
        Apartment secondApartment = new Apartment(2, oneRoom);
        Apartment thirdApartment = new Apartment(3, twoRooms);
        Apartment fourthApartment = new Apartment(4, twoRooms);
        Apartment fifthApartment = new Apartment(5, threeRooms);
        Apartment sixthApartment = new Apartment(6, threeRooms);

        Apartment[] apartmentsInFirstFloor = {firstApartment, secondApartment};
        Apartment[] apartmentsInSecondFloor = {thirdApartment, fourthApartment};
        Apartment[] apartmentsInThirdFloor = {fifthApartment, sixthApartment};

        Floor firstFloor = new Floor(1, apartmentsInFirstFloor);
        Floor secondFloor = new Floor(2, apartmentsInSecondFloor);
        Floor thirdFloor = new Floor(3, apartmentsInThirdFloor);

        Floor[] floors = {firstFloor, secondFloor, thirdFloor};

        Build build = new Build(23, floors);

        printAllInformation(build);
    }

    public static void printAllInformation(Build build) {
        System.out.println("Номер дома " + build.getNumberOfBuild());
        for (int i = 0; i < build.getFloor().length; i++) {
            build.getFloor()[i].print();
            for (int j = 0; j < build.getFloor()[i].getApartment().length; j++) {
                build.getFloor()[i].getApartment()[j].print();
                for (int k = 0; k < build.getFloor()[i].getApartment()[j].getRooms().length; k++) {
                    build.getFloor()[i].getApartment()[j].getRooms()[k].print();
                }
            }
        }
    }
}


