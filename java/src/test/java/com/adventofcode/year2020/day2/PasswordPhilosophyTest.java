package com.adventofcode.year2020.day2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordPhilosophyTest {

    PasswordPhilosophy sut = new PasswordPhilosophy();

    @Test
    void firstPart() {
        assertEquals(546, sut.part1());
    }

    @Test
    void secondPart() {
        assertEquals(275, sut.part2());
    }

    @Test
    void matchesLetterExactlyOnce() {
        assertTrue(sut.isTheLetterPresentExactlyOnce('a', 'a', 'b'));
        assertTrue(sut.isTheLetterPresentExactlyOnce('a', 'b', 'a'));
        assertFalse(sut.isTheLetterPresentExactlyOnce('a', 'a', 'a'));
        assertFalse(sut.isTheLetterPresentExactlyOnce('b', 'a', 'a'));
    }
}
