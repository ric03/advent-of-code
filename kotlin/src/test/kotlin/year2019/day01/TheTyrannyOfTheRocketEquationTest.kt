package year2019.day01

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import year2019.calculateRequiredFuel
import year2019.calculateRequiredFuelSum

class TheTyrannyOfTheRocketEquationTest : FreeSpec({

  "Calculate fuel required for mass" - {
    listOf(
      12 to 2,
      14 to 2,
      1969 to 654,
      100756 to 33583,
    ).forEach { (mass: Int, expectedFuel: Int) ->
      "it should convert $mass to $expectedFuel" {
        calculateRequiredFuel(mass) shouldBe expectedFuel
      }
    }
  }

  "Calculate the sum of required fuel" {
    val resourceFile = "src/test/resources/2019/01.1-test.txt"
    calculateRequiredFuelSum(resourceFile) shouldBe (2 + 2 + 654 + 33583)
  }
})
