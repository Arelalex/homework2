package com.dmdev.cs.homework.сycles;

/**
 * Дано целое число.
 * Написать функцию, которая принимает целое число, а возвращает целое число обратное этому (не строку!).
 * Результат вывести на консоль.
 * Например: 4508 -> 8054, 4700 -> 74, 1234567 -> 7654321
 * <p>
 * Примечание: для решения может понадобится функция возведение числа в степень: Math.pow(число, степень).
 */

public class Task2 {
    public static void main(String[] args) {
        System.out.println(getReverseValue(4508));
        System.out.println(getReverseValue(4700));
        System.out.println(getReverseValue(1234567));
    }

    private static int getReverseValue(int value) {
        int result = 0;
        for (int currentValue = (value > 0 ? value : value * -1); currentValue > 0; currentValue /= 10) {
            int remainder = currentValue % 10;
            result = result * 10 + remainder;
        }
        return result;
    }
}
