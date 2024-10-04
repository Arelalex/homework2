package com.dmdev.java_core.oop.collection.hw1;

import java.util.*;

/**
 * Дан список чатов.
 * Каждый чат состоит из двух полей: название и количество пользователей в этом чате.
 * <p>
 * Задача:
 * - Удалить с помощью итератора из этого списка те чаты, что содержат менее 1000 пользователей.
 * - Оставшиеся чаты отсортировать с помощью компараторов по убыванию по количеству пользователей, а если это количество
 * совпадает, то по названию в алфавитном порядке.
 * - Также предоставить сортировку чатов по названию по умолчанию.
 */

public class Task1 {
    public static void main(String[] args) {

        List<Chat> sourceList = Arrays.asList(
                new Chat("GPT", 999),
                new Chat("Oop", 3400),
                new Chat("Test", 1234),
                new Chat("Abc", 23400),
                new Chat("Def", 999),
                new Chat("Java", 1000),
                new Chat("Test_Test", 3400)
        );
        List<Chat> chatList = new ArrayList<>(sourceList);

        deleteChat(chatList);

        chatList.sort(new NumberOfUsersComparator());
        System.out.println(chatList);

        Collections.sort(chatList);
        System.out.println(chatList);
    }

    public static void deleteChat(List<Chat> chatList) {
        for (Iterator<Chat> iterator = chatList.iterator(); iterator.hasNext(); ) {
            Chat next = iterator.next();
            if (next.getNumberOfUsers() < 1000) {
                iterator.remove();
            }
        }
    }

    public static class NumberOfUsersComparator implements Comparator<Chat> {
        @Override
        public int compare(Chat o1, Chat o2) {
            if (o1.getNumberOfUsers().equals(o2.getNumberOfUsers())) {
                return o1.getName().compareTo(o2.getName());
            } else return o1.getNumberOfUsers().compareTo(o2.getNumberOfUsers());
        }
    }
}
