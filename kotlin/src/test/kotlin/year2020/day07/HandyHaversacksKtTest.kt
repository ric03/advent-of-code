package year2020.day07

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HandyHaversacksKtTest : FunSpec({

  val resourceFile = "src/test/resources/2020/07-test.txt"

  test("mapStringToBagPair should return no containing Bag") {
    val result = mapStringToBagPair("faded blue bags contain no other bags.")
    result shouldBe ("faded blue" to emptyMap())
  }

  test("mapStringToBagPair should return a single Bag Pair") {
    val result = mapStringToBagPair("light red bags contain 1 bright yellow bag.")
    result shouldBe ("light red" to mapOf("bright yellow" to 1))
  }
  test("mapStringToBagPair should return two Bag Pairs") {
    val result = mapStringToBagPair("light red bags contain 1 bright yellow bag, 6 dotted black bags.")
    result shouldBe ("light red" to mapOf("bright yellow" to 1, "dotted black" to 6))
  }
  test("mapStringToBagPair should return four Bag Pairs") {
    val result =
      mapStringToBagPair("shiny plum bags contain 1 muted indigo bag, 2 light lime bags, 4 clear coral bags, 2 dim lavender bags.")
    result shouldBe ("shiny plum" to mapOf(
      "muted indigo" to 1,
      "light lime" to 2,
      "clear coral" to 4,
      "dim lavender" to 2
    ))
  }

  test("countShinyGoldBags") {
    val map = generateBagMap(resourceFile)
    map.countShinyGoldBags() shouldBe 4
  }
})
