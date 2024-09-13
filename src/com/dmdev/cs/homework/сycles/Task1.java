package com.dmdev.cs.homework.сycles;

/**
 * Посчитать четные и нечетные цифры целого числа и вывести их на консоль.
 * Для решения написать 2 функции, которые будут принимать введенное целое число, а возвращать количество четных цифр (вторая функция - нечетных).
 * <p>
 * Например: если введено число 228910, то у него 4 четные цифры (2, 2, 8, 0) и 2 нечетные (9, 1).
 */

public class Task1 {
    public static void main(String[] args) {
        int value = 228910;
        isCountEvenNumber(value);
        isCountOddNumber(value);
    }

    private static int isCountEvenNumber(int value) {
        int result = 0;
        for (int currentValue = (value > 0 ? value : value * -1); currentValue > 0; currentValue /= 10) {
            int remainder = currentValue % 10;
            if (remainder % 2 == 0) {
                System.out.println("Четная цифра: " + remainder);
                result++;
            }
        }
        System.out.println("Количество четных цифр в числе " + result);
        return result;
    }

    private static int isCountOddNumber(int value) {
        int result = 0;
        for (int currentValue = (value > 0 ? value : value * -1); currentValue > 0; currentValue /= 10) {
            int remainder = currentValue % 10;
            if (remainder % 2 != 0) {
                System.out.println("Нечетная цифра: " + remainder);
                result++;
            }
        }
        System.out.println("Количество нечетных цифр в числе " + result);
        return result;
    }
}
