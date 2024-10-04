package com.dmdev.java_core.oop.collection.hw2;

public class User implements Comparable<User>{
    private Integer id;
    private String userName;
    private Integer age;

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return age.compareTo(o.age);
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
