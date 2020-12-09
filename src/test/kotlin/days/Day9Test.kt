package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day9Test {
    private val dayNine = Day9()

    @Test
    fun testPartOne() {
        Day9.preambleSize = 5
        assertThat(dayNine.partOne(), `is`(127L))
    }

    @Test
    fun testPartTwo() {
        Day9.preambleSize = 5
        Day9.partTwoTarget = 127L
        assertThat(dayNine.partTwo(), `is`(62L))
    }
}
