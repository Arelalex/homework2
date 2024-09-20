package com.dmdev.java_core.oop.String;

import static java.lang.Character.toUpperCase;

/**
 * Дана строка.
 * Удалить из нее все повторяющиеся символы без учета регистра, если они идут друг за другом. Также удалить пробелы.
 * Результат привести к верхнему регистру.
 * <p>
 * Например:
 * "abc Cpddd Dio OsfWw" -> "ABCPDIOSFW"
 */
public class Task1 {
    public static void main(String[] args) {
        String string = "abc Cpddd Dio OsfWw";
        System.out.println(removeDuplicate(removeSpace(string)));
    }

    private static String removeSpace(String string) {
        String stringWithoutSpaces = string.replace(" ", "");
        return stringWithoutSpaces;
    }

    private static String removeDuplicate(String string) {
        char lastChar = 0;
        for (int i = 0; i < string.length(); i++) {
            if (i == 0) {
                lastChar = string.charAt(i);
            } else if (toUpperCase(lastChar) == string.toUpperCase().charAt(i)) {
                lastChar = string.charAt(i);
                string = string.replace(string.charAt(i), ' ');
            }
            else {
                lastChar = string.charAt(i);
            }
        }
        return removeSpace(string.toUpperCase());
    }
}

