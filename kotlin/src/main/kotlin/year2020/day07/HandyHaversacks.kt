package year2020.day07

import java.io.File
import java.util.regex.Pattern

fun main() {
  val resourceFile = "kotlin/src/main/resources/2020/07-handy-haversacks.txt"
  val bags = generateBagMap(resourceFile)
  val sumOfBagColors = bags.countShinyGoldBags()
  println("[01] The number of bag colors that can eventually contain at least one shiny gold bag is $sumOfBagColors")
  println("[02] ...")
}

fun Map<String, Map<String, Int>>.countShinyGoldBags(): Int {
  return this.keys.fold(0) { acc, color -> acc + if (this.searchRecursivelyForShinyGold(color)) 1 else 0 }
}

fun Map<String, Map<String, Int>>.searchRecursivelyForShinyGold(color: String): Boolean {
  if (this.values.isEmpty()) return false
  if (this[color]!!.keys.find { it == "shiny gold" } != null) return true
  if (this[color]!!.keys.find { this.searchRecursivelyForShinyGold(it) } != null) return true

  return false
}

fun generateBagMap(resourceFile: String): Map<String, Map<String, Int>> =
  File(resourceFile)
    .readLines()
    .associate { mapStringToBagPair(it) }


fun mapStringToBagPair(str: String): Pair<String, Map<String, Int>> {
  val bagColor = str.split(" bags contain ").first()
  val contentString = str.split(" bags contain ").last()
  val bagContent: Map<String, Int> =
    if (contentString.contains("no other bags")) emptyMap() else contentString.split(",").map { it.trim() }
      .associate {
        val bag = Pattern.compile("(?<count>\\d+) (?<color>\\w+ \\w+) bags?").matcher(it)
        bag.find()
        bag.group("color") to bag.group("count").toInt()
      }

  return bagColor to bagContent
}

