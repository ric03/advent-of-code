package com.adventofcode.day2;

import com.adventofcode.FileUtils;
import lombok.Builder;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Day 2: Password Philosophy
 */
public class PasswordPhilosophy {

    long part1() {
        return FileUtils
            .readResourceFile("day-2.txt")
            .map(this::mapToPassword)
            .filter(this::validatePassword)
            .count();
    }

    long part2() {
        return FileUtils
            .readResourceFile("day-2.txt")
            .map(this::mapToPassword)
            .filter(this::validatePasswordPosition)
            .count();
    }

    Password mapToPassword(String input) {
        Pattern pattern = Pattern.compile("([0-9]+)-([0-9]+) ([a-z]): ([a-z]+)");
        Matcher matcher = pattern.matcher(input);
        //noinspection ResultOfMethodCallIgnored
        matcher.matches();
        MatchResult matchResult = matcher.toMatchResult();

        if (matchResult.groupCount() == 4) {

            PasswordPolicy passwordPolicy = PasswordPolicy.builder()
                .min(Integer.parseInt(matchResult.group(1)))
                .max(Integer.parseInt(matchResult.group(2)))
                .letter(matchResult.group(3).charAt(0))
                .build();
            String password = matchResult.group(4);

            return Password.builder()
                .password(password)
                .policy(passwordPolicy)
                .build();
        } else {
            throw new InputMismatchException("The input string does not match the pattern");
        }
    }

    boolean validatePassword(Password password) {
        PasswordPolicy policy = password.getPolicy();
        char letter = policy.getLetter();

        long letterCount = password
            .getPassword().chars()
            .filter(c -> c == letter)
            .count();

        return policy.getMin() <= letterCount && letterCount <= policy.getMax();
    }

    boolean validatePasswordPosition(Password password) {
        PasswordPolicy policy = password.getPolicy();
        char letter = policy.getLetter();
        char firstLetter = '0';
        char secondLetter = '0';
        try {
            firstLetter = password.getPassword().charAt(policy.getMin() - 1);
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            secondLetter = password.getPassword().charAt(policy.getMax() - 1);
        } catch (IndexOutOfBoundsException ignored) {
        }

        return isTheLetterPresentExactlyOnce(letter, firstLetter, secondLetter);
    }

    boolean isTheLetterPresentExactlyOnce(char letter, char firstLetter, char secondLetter) {
        return (firstLetter == letter && secondLetter != letter)
            || (firstLetter != letter && secondLetter == letter);
    }

    @Getter
    @Builder
    private static class Password {
        private final String password;
        private final PasswordPolicy policy;
    }

    @Getter
    @Builder
    private static class PasswordPolicy {
        private final int min;
        private final int max;
        private final char letter;
    }
}
