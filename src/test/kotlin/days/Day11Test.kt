package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day11Test {
    private val dayTen = Day11()

    @Test
    fun testPartOne() {
        assertThat(dayTen.partOne(), `is`(37))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTen.partTwo(), `is`(26))
    }
}
