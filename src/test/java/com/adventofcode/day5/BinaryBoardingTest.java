package com.adventofcode.day5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinaryBoardingTest {

    private BinaryBoarding sut = new BinaryBoarding();

    @ParameterizedTest
    @CsvSource({"BFFFBBFRRR,567", "FFFBBBFRRR,119", "BBFFBBFRLL,820"})
    void test(String boardingPass, Integer seatId) {
        assertEquals(seatId, sut.getSeatId(boardingPass));
    }

    @Test
    void part1() {
        assertEquals(828, sut.getHighestSeatId());
    }

    @Test
    void part2() {
        Optional<Integer> mySeat = sut.findMySeat();

        assertTrue(mySeat.isPresent());
        assertEquals(565, mySeat.get());
    }

}
