package com.adventofcode.year2020.day6;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

public class CustomCustoms {

    public int getSum(String customs) {
        List<List<String>> groups = Arrays.stream(customs.split("\n\n"))
            .map(group -> List.of(group.split("\n")))
            .collect(Collectors.toList());
        return -1;
    }

    private int countUniqueVotes(List<String> votes) {
        votes.stream()
            .map(s -> List.of(s.split("")))
            .reduce(Set.of(), addElementsToSet(), combineSet());
        return votes.stream().reduce(0, (acc, cur) -> acc + cur.length(), Integer::sum);
    }

    private BiFunction<Set<Object>, List<String>, Set<Object>> addElementsToSet() {
        return (acc, cur) -> {
            acc.addAll(cur);
            return acc;
        };
    }

    private BinaryOperator<Set<Object>> combineSet() {
        return (acc, cur) -> {
            acc.addAll(cur);
            return acc;
        };
    }
}
