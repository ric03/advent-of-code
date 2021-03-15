package com.adventofcode.day1;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportRepairTest {

    ReportRepair sut = new ReportRepair();

    @Test
    void firstPart() {
        Optional<Integer> result = sut.sumBy2();

        assertTrue(result.isPresent());
        assertEquals(result.get(), 1_015_476);
    }

    @Test
    void secondPart() {
        Optional<Integer> result = sut.sumBy3();

        assertTrue(result.isPresent());
        assertEquals(result.get(), 200_878_544);
    }
}
