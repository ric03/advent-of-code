package com.adventofcode.day1;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class ReportRepair {

    private static final int YEAR = 2020;

    /**
     * Part One
     */
    Optional<Integer> sumBy2() {
        List<Integer> numbers = readNumbers();

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                if (numbers.get(i) + numbers.get(j) == YEAR) {
                    return Optional.of(numbers.get(i) * numbers.get(j));
                }
            }
        }

        return Optional.empty();
    }

    /**
     * Part Two
     */
    Optional<Integer> sumBy3() {
        List<Integer> numbers = readNumbers();

        for (int i = 0; i < numbers.size(); i++) {
            for (int j = 0; j < numbers.size(); j++) {
                for (int k = 0; k < numbers.size(); k++) {
                    int sum = numbers.get(i) + numbers.get(j) + numbers.get(k);
                    if (sum == YEAR) {
                        return Optional.of(numbers.get(i) * numbers.get(j) * numbers.get(k));
                    }
                }
            }
        }

        return Optional.empty();
    }

    private List<Integer> readNumbers() {
        try {
            Path path = Paths.get("src/main/resources/day-1.txt");
            return Files
                .lines(path)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}
