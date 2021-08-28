package year2020.day06

import java.io.File

fun main() {
  val resourceFile = "kotlin/src/main/resources/2020/06-custom-customs.txt"
  val sumUnique = sumOfUniqueCustomsPerGroup(resourceFile)
  val sumSame = sumOfSameCustomsPerGroup(resourceFile)
  println("[01] The sum of unique customs in a group is $sumUnique")
  println("[02] The sum of same customs in a group is $sumSame")
}

// --- PART 1 --- //
fun sumOfUniqueCustomsPerGroup(resourceFile: String): Int =
  File(resourceFile)
    .readLines()
    .toList()
    .fold(mutableListOf(mutableSetOf()), groupByUniqueCustomsForeachGroup())
    .map { it.size }
    .reduce { acc, i -> acc + i }

private fun groupByUniqueCustomsForeachGroup() = { acc: MutableList<MutableSet<Char>>, line: String ->
  if (line.isNotBlank()) {
    acc.last().addAll(line.toCharArray().toSet())
  } else {
    acc.add(mutableSetOf())
  }
  acc
}

// --- PART 2 --- //
fun sumOfSameCustomsPerGroup(resourceFile: String): Int =
  File(resourceFile)
    .readText()
    .split("\n\n") // split into groups
    .map { countSameCustomsPerGroup(it) }
    .reduce { acc, i -> acc + i }

fun countSameCustomsPerGroup(group: String): Int {
  val lines = group.split('\n').filter { it.isNotBlank() }
  val linesAsSet = lines.map { it.toCharArray().toMutableSet() }
  val reducedSet = linesAsSet.reduce { acc, mutableSet -> acc.retainAll(mutableSet); acc }
  return reducedSet.size
}
