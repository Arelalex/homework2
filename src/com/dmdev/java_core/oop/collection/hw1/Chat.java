package com.dmdev.java_core.oop.collection.hw1;

public class Chat implements Comparable<Chat> {
    private String name;
    private Integer numberOfUsers;

    public Chat(String name, Integer numberOfUsers) {
        this.name = name;
        this.numberOfUsers = numberOfUsers;
    }

    public String getName() {
        return name;
    }

    public Integer getNumberOfUsers() {
        return numberOfUsers;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "name='" + name + '\'' +
                ", numberOfUsers=" + numberOfUsers +
                '}';
    }

    @Override
    public int compareTo(Chat o) {
        return name.compareTo(o.name);
    }
}
