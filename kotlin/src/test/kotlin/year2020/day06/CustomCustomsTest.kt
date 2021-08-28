package year2020.day06

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class CustomCustomsTest : FreeSpec({
  val resourceFile = "src/test/resources/2020/06-test.txt"

  "Calculate the sum of unique customs per group" {
    sumOfUniqueCustomsPerGroup(resourceFile) shouldBe 11
  }

  "Calculate the sum of same customs per group" {
    sumOfSameCustomsPerGroup(resourceFile) shouldBe 6
  }
})
