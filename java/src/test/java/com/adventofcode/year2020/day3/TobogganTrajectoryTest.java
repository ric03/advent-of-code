package com.adventofcode.year2020.day3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TobogganTrajectoryTest {

    private TobogganTrajectory sut = new TobogganTrajectory();

    @Test
    void firstPart() {
        assertEquals(173, sut.part1("day-3.txt"));
    }

    @Test
    void secondPartExample() {
        assertEquals(336, sut.part2("day-3-part2-example.txt"));
    }

    @Test
    void secondPart() {
        assertEquals(4385176320L, sut.part2("day-3.txt"));
    }
}
