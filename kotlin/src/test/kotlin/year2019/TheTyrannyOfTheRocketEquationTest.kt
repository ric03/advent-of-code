package year2019

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

@Suppress("unused")
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
    calculateRequiredFuelSum("src/test/resources/2019/day01-tom.txt") shouldBe 3268951
  }
})
