package com.adventofcode.year2020.day3;

import com.adventofcode.FileUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class TobogganTrajectory {

    long part1(String filename) {
        Forest forest = new Forest(filename);
        return forest.countTrees(new Slope(3, 1));
    }

    long part2(String filename) {
        Forest forest = new Forest(filename);
        return forest.countTrees(new Slope(1, 1))
            * forest.countTrees(new Slope(3, 1))
            * forest.countTrees(new Slope(5, 1))
            * forest.countTrees(new Slope(7, 1))
            * forest.countTrees(new Slope(1, 2));
    }


    static class Forest {

        private final List<String> forest;
        private final int lineLength;

        public Forest(String filename) {
            forest = FileUtils
                .readResourceFile(filename)
                .collect(Collectors.toList());

            lineLength = forest.get(0).length();
        }

        public long countTrees(Slope slope) {
            int treeCount = 0;
            int x = 0;
            int y = 0;

            while (y < forest.size()) {
                if (isTree(x, y)) {
                    treeCount++;
                }

                x += slope.getX();
                y += slope.getY();
            }

            return treeCount;
        }


        private boolean isTree(int x, int y) {
            char squareType = forest.get(y).charAt(x % lineLength);
            return squareType == SquareType.TREE.getSign();
        }
    }

    @Getter
    @AllArgsConstructor
    enum SquareType {
        TREE('#'),
        OPEN('.');

        private final char sign;
    }

    @Getter
    @AllArgsConstructor
    static class Slope {
        private final int x;
        private final int y;
    }
}


