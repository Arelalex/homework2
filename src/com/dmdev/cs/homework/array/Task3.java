package com.dmdev.cs.homework.array;

/**
 * Дан одномерный массив целых чисел.
 * <p>
 * Написать функцию, которая принимает этот массив и разбивает на 3 других: с только отрицательными числами, только положительными и только нули.
 * Если для какого-то из массивов не будет значений, то должен быть создан пустой массив.
 * Возвращает функция эти три массива в виде одного двумерного.
 */
public class Task3 {
    public static void main(String[] args) {
        int[] array = {-4, 0, 1, 9, 0, -18, 3};
        int[][] newTwoDimensionalArray = createArray(array, calculateLengthOfArray(array));
        printArray(newTwoDimensionalArray);
        System.out.println();

        int[] arrayWithoutNull = {-4, 1, 9, -18, 3};
        int[][] newTwoDimensionalArrayWithoutNull = createArray(arrayWithoutNull, calculateLengthOfArray(arrayWithoutNull));
        printArray(newTwoDimensionalArrayWithoutNull);
        System.out.println(newTwoDimensionalArrayWithoutNull[2]);

        int[] arrayWithoutNegativeNumbers = {0, 1, 9, 0, 3};
        int[][] newTwoDimensionalArrayWithoutNegativeNumbers = createArray(arrayWithoutNegativeNumbers, calculateLengthOfArray(arrayWithoutNegativeNumbers));
        printArray(newTwoDimensionalArrayWithoutNegativeNumbers);
        System.out.println(newTwoDimensionalArrayWithoutNegativeNumbers[0]);

        int[] arrayWithoutPositiveNumbers = {-4, 0, 0, -18};
        int[][] newTwoDimensionalArrayWithoutPositiveNumbers = createArray(arrayWithoutPositiveNumbers, calculateLengthOfArray(arrayWithoutPositiveNumbers));
        printArray(newTwoDimensionalArrayWithoutPositiveNumbers);
        System.out.println(newTwoDimensionalArrayWithoutPositiveNumbers[1]);
    }

    private static void printArray(int[][] array) {
        for (int i : array[0]) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : array[1]) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : array[2]) {
            System.out.print(i + " ");
        }
    }

    private static int[][] createArray(int[] array, int[][] arrayNew) {
        int negativeCount = 0;
        int positiveCount = 0;
        int nullCount = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0) {
                arrayNew[0][negativeCount] = array[i];
                negativeCount++;

            } else if (array[i] > 0) {
                arrayNew[1][positiveCount] = array[i];
                positiveCount++;

            } else {
                arrayNew[2][nullCount] = array[i];
                nullCount++;
            }
        }
        return arrayNew;
    }

    private static int[][] calculateLengthOfArray(int[] array) {
        int negativeCount = 0;
        int positiveCount = 0;
        int nullCount = 0;
        for (int j : array) {
            if (j < 0) {
                negativeCount++;
            } else if (j > 0) {
                positiveCount++;
            } else {
                nullCount++;
            }
        }
        int[][] arrayNew = new int[array.length][];
        arrayNew[0] = new int[negativeCount];
        arrayNew[1] = new int[positiveCount];
        arrayNew[2] = new int[nullCount];
        return arrayNew;
    }
}


