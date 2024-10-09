package com.dmdev.java_core.oop.collection.hw2;

import com.dmdev.java_core.oop.collection.hw2.chat.Chat;
import com.dmdev.java_core.oop.collection.hw2.chat.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.dmdev.java_core.oop.collection.hw2.util.UtilActionChat.calculateAverageAge;
import static com.dmdev.java_core.oop.collection.hw2.util.UtilActionChat.convertToSingleList;

/**
 * Дан список чатов с предыдущего задания, только вместо поля “количество пользователей” будет список объектов
 * типа Пользователь, который имеет 3 поля: id (целочисленный идентификатор), имя и возраст.
 * Задача:
 * - Преобразовать список чатов в один список пользователей всех чатов, возраст которых больше 18 лет
 * - С помощью итератора посчитать средний возраст всех оставшихся пользователей.
 */
public class Task2 {
    public static void main(String[] args) {

        List<User> usersSource1 = Arrays.asList(
                new User(1, "Ivan", 16),
                new User(2, "Petr", 18)
        );
        List<User> userListChat1 = new ArrayList<>(usersSource1);

        List<User> usersSource2 = Arrays.asList(
                new User(1, "Sveta", 12),
                new User(2, "Nikita", 31)
        );
        List<User> userListChat2 = new ArrayList<>(usersSource2);

        List<User> usersSource3 = Arrays.asList(
                new User(5, "Aleksandra", 24),
                new User(6, "Radgesh", 41)
        );
        List<User> userListChat3 = new ArrayList<>(usersSource3);


        List<Chat> sourceList = Arrays.asList(
                new Chat("GPT", userListChat1),
                new Chat("Oop", userListChat2),
                new Chat("Test", userListChat3)
        );
        List<Chat> chatList = new ArrayList<>(sourceList);

        System.out.println(convertToSingleList(chatList));
        System.out.println(calculateAverageAge(convertToSingleList(chatList)));
    }
}

