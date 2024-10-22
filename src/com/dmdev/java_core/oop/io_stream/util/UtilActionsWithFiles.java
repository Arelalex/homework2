package com.dmdev.java_core.oop.io_stream.util;

import com.dmdev.java_core.oop.io_stream.auxiliary.Items;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class UtilActionsWithFiles {

    private static int firstLine = 1;

    private UtilActionsWithFiles() {

    }

    public static void createDirectory() throws IOException {
        Path addDirectory = Path.of("resources");
        if (!Files.isDirectory(addDirectory)) {
            try {
                Files.createDirectory(addDirectory);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void createFiles(Path path1, Path path2) throws IOException {
        try {
            Files.write(path1, List.of("ID,PRICE", "1,9.98", "2,25.05", "3,16.45", "4", "5,6.43", "6"));
            Files.write(path2, List.of("ID,NAME,DESCRIPTION", "1,Шарф,Теплый зимний шарф красного цвета",
                    "2,Шапка,Вязаная зеленая шапка", "3,Ботинки,Осенние ботинки на толстой подошве", "4,Rehnrf", "5"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Items> getMapFromFileWithName(Path path2, Map<String, Items> itemsMapFromFile2) {
        try {
            Files.lines(path2)
                    .skip(firstLine)
                    .map(line -> line.split(","))
                    .forEach(array -> {
                        Items item = new Items();
                        item.setName(array.length > 1 ? array[1] : null);
                        itemsMapFromFile2.put(array[0], item);
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return itemsMapFromFile2;
    }

    public static Map<String, Items> getMapFromFileWithPrice(Path path1, Map<String, Items> itemsMapFromFile1) {
        try {
            Files.lines(path1)
                    .skip(firstLine)
                    .map(line -> line.split(","))
                    .forEach(array -> {
                        Items item = new Items();
                        item.setPrice(array.length > 1 ? array[1] : null);
                        itemsMapFromFile1.put(array[0], item);
                    });
        } catch (IOException e) {
            System.out.println("[ERROR] createFiles " + e.getMessage());
            throw new RuntimeException(e);
        }
        return itemsMapFromFile1;
    }

    public static Map<String, Items> getResultMap(Map<String, Items> itemsMapFromFile1, Map<String, Items> itemsMapFromFile2) {
        Map<String, Items> resultMap = new HashMap<>(itemsMapFromFile1);

        itemsMapFromFile2.forEach((k, v) -> resultMap.merge(k, v, (itemPrice, newPrice) -> {
            itemPrice.setName(newPrice.getName());
            return itemPrice;
        }));
        return resultMap;
    }

    public static Map<String, Items> getMapWithCorrectResult(Map<String, Items> resultMap) {
        Map<String, Items> mapWithCorrectValue = resultMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getName() != null)
                .filter(entry -> entry.getValue().getPrice() != null)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
        return mapWithCorrectValue;
    }

    public static Map<String, Items> getMapWithIncorrectResult(Map<String, Items> resultMap) {
        Map<String, Items> mapWithIncorrectValue = resultMap
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getName() == null || entry.getValue().getPrice() == null)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));
        return mapWithIncorrectValue;
    }

    public static void writeResultInFile(Map<String, Items> mapWithCorrectValue, File result) throws IOException {
        List<String> correctItemsList = mapWithCorrectValue.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "," + entry.getValue().getName() + "," + entry.getValue().getPrice())
                .toList();

        try (BufferedWriter writer1 = new BufferedWriter(new FileWriter(result))) {
            for (String line : correctItemsList) {
                writer1.write(line);
                writer1.newLine();
            }
        }
    }

    public static void writeResultInErrorsFile(Map<String, Items> mapWithIncorrectValue, File errors) throws IOException {
        List<String> inCorrectItemsList = mapWithIncorrectValue.keySet()
                .stream()
                .toList();

        try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(errors))) {
            for (String line : inCorrectItemsList) {
                writer2.write(line);
                writer2.newLine();
            }
        }
    }
}
