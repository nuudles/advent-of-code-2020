package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day10Test {
    private val dayTen = Day10()

    @Test
    fun testCalculate() {
        dayTen.calculate(listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)).let {
            assertThat(it.first, `is`(7))
            assertThat(it.second, `is`(5))
        }
    }

    @Test
    fun testPartOne() {
        assertThat(dayTen.partOne(), `is`(220))
    }

    @Test
    fun testCombinations() {
        assertThat(dayTen.combinations(listOf(1,4,5,6,7)), `is`(4))
        assertThat(
            dayTen.combinations(listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)),
            `is`(8)
        )
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTen.partTwo(), `is`(19208))
    }
}
