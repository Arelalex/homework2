package com.dmdev.java_core.oop.String;

/**
 * Дана строка с текстом, в котором есть цифры от 0 до 9.
 * <p>
 * Написать 2 метода:
 * <p>
 * - возвращающий массив цифр из переданной строки
 * - возвращающий сумму цифр из переданного целочисленного массива
 * <p>
 * Посчитать сумму всех чисел из строки
 * <p>
 * Например:
 * “Привет 8, как 1 2 твои дела? Может4, сделать 3 дело?” -> 18 (8+1+2+4+3)
 */
public class Task2 {
    public static void main(String[] args) {

        String string = "Привет 8, как 1 2 твои дела? Может4, сделать 3 дело?";
        System.out.println(sum(createArrayOfNumbersFromString(calculateArrayLength(string), string)));
    }

    private static int[] calculateArrayLength(String string) {
        int countForLenghtOfArray = 0;
        char[] charArray = string.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(string.charAt(i))) {
                countForLenghtOfArray++;
            }
        }
        return new int[countForLenghtOfArray];
    }


    private static int[] createArrayOfNumbersFromString(int[] array, String string) {
        int count = 0;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                array[count] = Character.getNumericValue(string.charAt(i));
                count++;
            }
        }
        return array;
    }

    private static int sum(int[] intArray) {
        int result = 0;
        for (int j : intArray) {
            result += j;
        }
        return result;
    }
}
