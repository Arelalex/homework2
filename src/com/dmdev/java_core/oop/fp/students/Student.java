package com.dmdev.java_core.oop.fp.students;

import java.util.List;

public class Student {

    private String firstName;
    private String lastName;
    private Integer courseNumber;
    private List<Integer> markList;

    public Student(String firstName, String lastName, Integer courseNumber, List<Integer> markList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.courseNumber = courseNumber;
        this.markList = markList;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

    public Integer getCourseNumber() {
        return courseNumber;
    }

    public List<Integer> getMarkList() {
        return markList;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courseNumber=" + courseNumber +
                ", markList=" + markList +
                '}';
    }
}
