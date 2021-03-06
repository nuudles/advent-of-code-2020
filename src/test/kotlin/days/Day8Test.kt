package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day8Test {
    private val dayEight = Day8()

    @Test
    fun testPartOne() {
        assertThat(dayEight.partOne(), `is`(5))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEight.partTwo(), `is`(8))
    }
}
