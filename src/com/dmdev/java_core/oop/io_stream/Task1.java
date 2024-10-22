package com.dmdev.java_core.oop.io_stream;

import com.dmdev.java_core.oop.io_stream.Auxiliary.Items;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import static com.dmdev.java_core.oop.io_stream.util.UtilActionsWithFiles.*;

/**
 * Даны два файла в формате CSV.
 * Формат первого файла items-price.csv:
 * ID,PRICE
 * 1,9.98
 * 2,25.05
 * 3,16.45
 * Формат второго файла items-name.csv:
 * ID,NAME,DESCRIPTION
 * 1,Шарф,Теплый зимний шарф красного цвета
 * 2,Шапка,Вязаная зеленая шапка
 * 3,Ботинки,Осенние ботинки на толстой подошве
 * Задача:
 * Считать оба CSV файла и объединить их по полю ID в один result.csv, где будут следующие поля: ID,NAME,PRICE.
 * Желательно реализовать доп. функционал:
 * Если для каких-то ID не будет значений в обоих файлах, то записать их в один файл errors.csv, где будет лишь одно поле ID.
 */

public class Task1 {
    public static void main(String[] args) throws IOException {

        Path path1 = Path.of("resources", "items-price.csv");
        Path path2 = Path.of("resources", "items-name.csv");

        Map<String, Items> itemsMapFromFile1 = new HashMap<>();
        Map<String, Items> itemsMapFromFile2 = new HashMap<>();

        File result = Path.of("resources", "result.csv").toFile();
        File errors = Path.of("resources", "errors.csv").toFile();

        createDirectory();
        createFiles(path1, path2);
        getMapFromFile2(path2,itemsMapFromFile2);
        getMapFromFile1(path1,itemsMapFromFile1);

        writeResultInFile(getMapWithCorrectResult(getResultMap(itemsMapFromFile1, itemsMapFromFile2)),result);
        writeResultInErrorsFile(getMapWithIncorrectResult(getResultMap(itemsMapFromFile1, itemsMapFromFile2)),errors);

    }
}



