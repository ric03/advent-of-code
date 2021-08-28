package year2019

import java.io.File

fun main() {
  // The path has to be relative to the project root (advent-of-code/<lang>/.../resource
  val resourceFile = "kotlin/src/main/resources/2019/01.1-tom-the-tyranny-of-the-rocket-equation.txt"
  val sum = calculateRequiredFuelSum(resourceFile)
  println("[Tom] The sum of fuel requirements is $sum.")
}

fun calculateRequiredFuel(mass: Int): Int {
  return (mass / 3) - 2
}

fun calculateRequiredFuelSum(resourceFile: String): Int =
  File(resourceFile)
    .readLines()
    .toList()
    .map { it.toInt() }
    .map { calculateRequiredFuel(it) }
    .reduce { acc, curr -> acc + curr }

fun readCsv(): Unit = TODO("@Peter pls implement your csv reader :D")
