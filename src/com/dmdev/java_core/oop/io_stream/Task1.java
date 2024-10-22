package com.dmdev.java_core.oop.io_stream;

import com.dmdev.java_core.oop.io_stream.auxiliary.Items;

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

        Map<String, Items> itemsMapFromFileWithPrice = new HashMap<>();
        Map<String, Items> itemsMapFromFileWithName = new HashMap<>();

        File result = Path.of("resources", "result.csv").toFile();
        File errors = Path.of("resources", "errors.csv").toFile();

        createDirectory();
        createFiles(path1, path2);
        getMapFromFileWithName(path2,itemsMapFromFileWithName);
        getMapFromFileWithPrice(path1,itemsMapFromFileWithPrice);

        writeResultInFile(getMapWithCorrectResult(getResultMap(itemsMapFromFileWithPrice, itemsMapFromFileWithName)),result);
        writeResultInErrorsFile(getMapWithIncorrectResult(getResultMap(itemsMapFromFileWithPrice, itemsMapFromFileWithName)),errors);

    }
}



