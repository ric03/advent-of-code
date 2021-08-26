package year2019

import java.io.File

fun calculateRequiredFuel(mass: Int): Int {
  return (mass / 3) - 2
}

fun calculateRequiredFuelSum(resourceFile: String = "src/main/resources/2019/day01-tom.txt"): Int = File(resourceFile)
  .readLines().toList()
  .map { it.toInt() }
  .map { calculateRequiredFuel(it) }
  .reduce { acc, curr -> acc + curr }

fun readCsv() {
  TODO("@Peter pls implement your csv reader :D")
}
