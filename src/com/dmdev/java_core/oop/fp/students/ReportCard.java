package com.dmdev.java_core.oop.fp.students;

import java.util.List;

public class ReportCard {
    private List<Student> studentList;
    private Double averageMark;

    public ReportCard(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public Double getAverageMark() {
        this.averageMark = studentList.stream().flatMapToInt(student -> student
                        .getMarkList()
                        .stream()
                        .mapToInt(Integer::intValue))
                .average()
                .orElse(0.0);
        return averageMark;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "ReportСard{" +
                "studentList=" + studentList +
                ", averageMark=" + averageMark +
                '}';
    }
}
