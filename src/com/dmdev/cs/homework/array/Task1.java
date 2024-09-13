package com.dmdev.cs.homework.array;

/**
 * Дан одномерный массив целых чисел.
 * Написать функцию, удаляющую из него все отрицательные элементы (удалить - значит создать новый массив с только положительными элементами).
 * После удаления - умножить каждый элемент массива на его длину.
 * Например: [3, 5, -6, 3, 2, -9, 0, -123] -> [15, 25, 15, 10, 0]
 * <p>
 * Не забывать, что всю логику приложения нужно выносить в отдельные функции. main - только для тестирования написанного функционала.
 */
public class Task1 {
    public static void main(String[] args) {
        int[] array = {3, 5, -6, 3, 2, -9, 0, -123};
        createNewArray(array, calculateLengthOfArray(array));
    }

    private static int[] calculateLengthOfArray(int[] array) {
        int count = 0;
        for (int j : array) {
            if (j >= 0) {
                count++;
            }
        }
        return new int[count];
    }

    private static void createNewArray(int[] array, int[] newArray) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] >= 0) {
                newArray[count] = array[i] * newArray.length;
                count++;
                System.out.print(newArray[count - 1] + " ");
            }
        }
    }
}

