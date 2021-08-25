package com.adventofcode.year2020.day4;

import com.adventofcode.FileUtils;
import lombok.AllArgsConstructor;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@AllArgsConstructor
public class PassportProcessing {

	private final PassportValidator passportValidator;

	long countValidPassports(String filename) {
		return FileUtils
			.readResourceFile(filename)
			.collect(new PassportCollector())
			.stream()
			.filter(passportValidator::validate)
			.count();
	}

	static Map<PassportField, String> convertToPassport(String passportString) {
		return Arrays
			.stream(passportString.split(" "))
			.collect(Collectors.toMap(
				key -> PassportField.valueOf(key.split(":")[0]),
				value -> value.split(":")[1]
			));
	}


	@AllArgsConstructor
	enum LengthUnit {
		inches("in"), centimeters("cm");

		final String abbr;
	}


	enum EyeColor {
		amb, blu, brn, gry, grn, hzl, oth
	}

	@AllArgsConstructor
	enum PassportField {
		byr("Birth Year"),
		iyr("Issue Year"),
		eyr("Expiration Year"),
		hgt("Height"),
		hcl("Hair Color"),
		ecl("Eye Color"),
		pid("Passport ID"),
		cid("Country ID");

		private final String description;
	}

	private static class PassportCollector implements Collector<String, List<String>, List<Map<PassportField, String>>> {

		private boolean isNewPassport = true;

		@Override
		public Supplier<List<String>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BiConsumer<List<String>, String> accumulator() {
			return (passportStrings, line) -> {

				if (line.isBlank()) {
					isNewPassport = true;

				} else if (isNewPassport) {
					passportStrings.add(line);
					isNewPassport = false;

				} else {
					int last = passportStrings.size() - 1;
					String partialPassport = passportStrings.get(last);

					partialPassport += " " + line.trim();
					passportStrings.set(last, partialPassport);
				}
			};
		}

		@Override
		public BinaryOperator<List<String>> combiner() {
			return (left, right) -> {
				left.addAll(right);
				return left;
			};
		}

		@Override
		public Function<List<String>, List<Map<PassportField, String>>> finisher() {
			return strings -> strings.stream()
				.filter(it -> !it.isBlank())
				.map(PassportProcessing::convertToPassport)
				.collect(Collectors.toList());
		}

		@Override
		public Set<Characteristics> characteristics() {
			return Collections.emptySet();
		}
	}

	interface PassportValidator {
		boolean validate(Map<PassportField, String> passport);
	}

	static class LoosePassportValidator implements PassportValidator {

		@Override
		public boolean validate(Map<PassportField, String> passport) {
			return (passport.size() == 7 && !passport.containsKey(PassportField.cid))
				|| passport.size() == 8;
		}
	}

	static class StrictPassportValidator implements PassportValidator {
		@Override
		public boolean validate(Map<PassportField, String> passport) {
			try {
				validateYearRange(passport.get(PassportField.byr), 1920, 2002);
				validateYearRange(passport.get(PassportField.iyr), 2010, 2020);
				validateYearRange(passport.get(PassportField.eyr), 2020, 2030);
				validateHeight(passport.get(PassportField.hgt));
				validateHairColor(passport.get(PassportField.hcl));
				validateEyColor(passport.get(PassportField.ecl));
				validatePassportId(passport.get(PassportField.pid));
				ignoreCountryId();
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		private void validateYearRange(String yr, int min, int max) {
			int year = Integer.parseInt(yr);
			if (!(min <= year && year <= max)) {
				throw new IllegalArgumentException();
			}
		}

		private void validateHeight(String hgt) {
			new HeightValidator().validate(hgt);
		}

		private void validateHairColor(String hairColor) {
			if (!hairColor.matches("^#[a-f0-9]{6}$")) {
				throw new IllegalArgumentException();
			}
		}

		private void validateEyColor(String eyeColor) throws IllegalArgumentException {
			EyeColor.valueOf(eyeColor);
		}

		private void validatePassportId(String pid) {
			if (pid.length() != 9) {
				throw new IllegalArgumentException();
			}
		}

		private void ignoreCountryId() {
		}

		static class HeightValidator {

			void validate(String height) {
				var unit = getUnit(height);
				var value = parseValue(height, unit);
				validateValue(value, unit);
			}

			private LengthUnit getUnit(String height) {
				if (height.endsWith(LengthUnit.inches.abbr)) {
					return LengthUnit.inches;

				} else if (height.endsWith(LengthUnit.centimeters.abbr)) {
					return LengthUnit.centimeters;

				} else {
					throw new IllegalArgumentException("Missing Unit");
				}
			}

			private void validateValue(int value, LengthUnit unit) {
				if (unit == LengthUnit.centimeters) {
					validateCentimeters(value);

				} else if (unit == LengthUnit.inches) {
					validateInches(value);
				}
			}

			private void validateInches(int value) {
				if (!(59 <= value && value <= 76)) {
					throw new IllegalArgumentException("Invalid Height (in)");
				}
			}

			private void validateCentimeters(int value) {
				if (!(150 <= value && value <= 193)) {
					throw new IllegalArgumentException("Invalid Height (cm)");
				}
			}

			private int parseValue(String height, LengthUnit unit) {
				return Integer.parseInt(height.replace(unit.abbr, ""));
			}
		}
	}
}























