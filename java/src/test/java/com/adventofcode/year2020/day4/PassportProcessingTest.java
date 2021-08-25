package com.adventofcode.year2020.day4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PassportProcessingTest {

    @Test
    void part1Example() {
        PassportProcessing sut = new PassportProcessing(new PassportProcessing.LoosePassportValidator());
        assertEquals(2, sut.countValidPassports("day-4-part1-example.txt"));
    }

    @Test
    void part1() {
        PassportProcessing sut = new PassportProcessing(new PassportProcessing.LoosePassportValidator());
        assertEquals(239, sut.countValidPassports("day-4.txt"));
    }

    @Test
    void part2Example() {
        PassportProcessing sut = new PassportProcessing(new PassportProcessing.StrictPassportValidator());
        assertEquals(4, sut.countValidPassports("day-4-part2-example.txt"));
    }

    @Test
    void part2() {
        PassportProcessing sut = new PassportProcessing(new PassportProcessing.StrictPassportValidator());
        assertEquals(188, sut.countValidPassports("day-4.txt"));
    }
}
