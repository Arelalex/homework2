package com.dmdev.java_core.oop.fp;

import com.dmdev.java_core.oop.fp.students.ReportCard;
import com.dmdev.java_core.oop.fp.students.Student;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Дан список студентов с полями:
 * - Имя
 * - Фамилия
 * - Номер курса в университете
 * - Список оценок за учебу
 * <p>
 * Преобразовать этот список студентов в ассоциативный массив, где ключом является номер курса, а значением:
 * Средняя оценка студентов этого курса, количество оценок у которых больше 3-х
 * Список студентов данного курса, но только с полями Имя и Фамилия.
 * Список должен быть отсортированы по этим двум полям
 * Объект с двумя полями:
 * - Отсортированный список студентов с пункта 2
 * - Средняя оценка этих студентов
 * Подумать, как ассоциативный массив можно было представить в коде в виде отсортированного - TreeMap
 */

public class StudentRunner {
    public static void main(String[] args) {

        List<Integer> markListForStudent1 = List.of(4, 3);
        List<Integer> markListForStudent2 = List.of(3, 2, 3);
        List<Integer> markListForStudent3 = List.of(5, 5, 4, 5, 4);
        List<Integer> markListForStudent4 = List.of(5, 4, 4, 3);
        List<Integer> markListForStudent5 = List.of(5, 4, 4, 3, 5, 4, 5);

        List<Student> students = List.of(
                new Student("Ivan", "Ivanov", 1, markListForStudent1),
                new Student("Stive", "Jobs", 6, markListForStudent4),
                new Student("Ivan", "Petrov", 1, markListForStudent2),
                new Student("Petr", "Petrikov", 2, markListForStudent3),
                new Student("Alina", "Alinina", 2, markListForStudent4),
                new Student("Nick", "Nickov", 4, markListForStudent4),
                new Student("Slava", "Slavikov", 3, markListForStudent4),
                new Student("Sveta", "Svetikova", 3, markListForStudent5),
                new Student("Bob", "Bobikov", 4, markListForStudent3),
                new Student("Cat", "Kotilov", 4, markListForStudent1)
        );

        Map<Integer, Double> mapWithAverageMark = students.stream()
                .filter(student -> student.getMarkList().size() > 3)
                .collect(Collectors.groupingBy(Student::getCourseNumber,
                        Collectors.flatMapping(student -> student.getMarkList().stream(),
                                Collectors.averagingInt(Integer::intValue))));

        System.out.println(mapWithAverageMark);

        Map<Integer, List<String>> mapWithStudents = students.stream()
                .sorted(Comparator.comparing(Student::getFullName))
                .collect(Collectors.groupingBy(Student::getCourseNumber,
                        Collectors.mapping(
                                Student::getFullName,
                                Collectors.toList())));

        System.out.println(mapWithStudents);

        Map<Integer, ReportCard> mapWithObject = students.stream()
                .sorted(Comparator.comparing(Student::getFullName))
                .collect((Collectors.groupingBy(Student::getCourseNumber,
                        Collectors.collectingAndThen(
                                Collectors.toList(), ReportCard::new))));

        mapWithObject.forEach((k, v) -> System.out.println("average mark " + v.getAverageMark() + " for students from " + k + " course"));

        Map<Integer, List<Student>> mapTreeMap = students.stream()
                .collect(Collectors.groupingBy(Student::getCourseNumber,
                        TreeMap::new,
                        Collectors.toList()));

        mapTreeMap.forEach((k, v) -> System.out.println(k + " " + v));
    }
}
