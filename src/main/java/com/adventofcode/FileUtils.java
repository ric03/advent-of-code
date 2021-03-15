package com.adventofcode;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileUtils {

    private final static String resourceFolder = "src/main/resources";

    public static Stream<String> readResourceFile(String filename) {
        Path path = Path.of(resourceFolder + "/" + filename);
        try {
            return Files.lines(path);

        } catch (Exception e) {
            e.printStackTrace();
            return Stream.empty();
        }
    }

}
