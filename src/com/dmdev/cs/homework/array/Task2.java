package com.dmdev.cs.homework.array;

/**
 * Дан одномерный массив символов.
 * Преобразовать его в одномерный массив чисел, где число - это код символа (любой символ - это число в памяти компьютера).
 * Например: [‘a’, ‘6’, ‘y’, ‘P’, ‘T’, ‘q’, ‘9’, ‘+’] -> [97, 54, 121, 80, 84, 113, 57, 43]
 * <p>
 * Далее определить среднее арифметическое всех элементов целочисленного массива
 * и вывести на консоль только те элементы, которые больше этого среднего арифметического
 */
public class Task2 {
    public static void main(String[] args) {
        char[] array = {'a', '6', 'y', 'P', 'T', 'q', '9', '+' };
        showElementsGreaterThanArithmeticMean(convertCharacterToNumber(array));
    }

    private static int[] convertCharacterToNumber(char[] array) {
        int[] arrayWithInt = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayWithInt[i] = array[i];
        }
        return arrayWithInt;
    }

    private static int calculateTheArithmeticMean(int[] arrayWithInt) {
        int sum = 0;
        int result;
        for (int i = 0; i < arrayWithInt.length; i++) {
            sum += arrayWithInt[i];
        }
        result = sum / arrayWithInt.length;
        return result;
    }

    private static void showElementsGreaterThanArithmeticMean(int[] arrayWithInt) {
        for (int i = 0; i < arrayWithInt.length; i++) {
            if (arrayWithInt[i] > calculateTheArithmeticMean(arrayWithInt)) {
                System.out.print(arrayWithInt[i] + " ");
            }
        }
    }
}
