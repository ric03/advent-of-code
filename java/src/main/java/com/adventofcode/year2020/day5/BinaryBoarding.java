package com.adventofcode.year2020.day5;

import com.adventofcode.FileUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BinaryBoarding {

	public Integer getSeatId(String boardingPass) {
		int rowId = binaryToInt(boardingPass.substring(0, 7), "B");
		int columnId = binaryToInt(boardingPass.substring(7), "R");
		return generateSeatId(rowId, columnId);
	}

	private int binaryToInt(String binary, String binaryOne) {
		int accumulator = (int) Math.pow(2, binary.length());
		int value = 0;
		for (String bit : binary.split("")) {
			accumulator /= 2;
			if (binaryOne.equals(bit)) {
				value += accumulator;
			}
		}
		return value;
	}

	private int generateSeatId(int rowId, int columnId) {
		return rowId * 8 + columnId;
	}

	public int getHighestSeatId() {
		return FileUtils.readResourceFile("day-5.txt")
			.map(this::getSeatId)
			.max(Integer::compareTo).orElse(0);
	}

	public Optional<Integer> findMySeat() {
		List<Integer> seatIds = FileUtils.readResourceFile("day-5.txt")
			.map(this::getSeatId)
			.sorted()
			.collect(Collectors.toList());

		for (int i = 0; i < seatIds.size() - 1; i++) {
			if (seatIds.get(i) + 2 == seatIds.get(i + 1)) {
				return Optional.of(seatIds.get(i) + 1);
			}
		}
		return Optional.empty();
	}
}
