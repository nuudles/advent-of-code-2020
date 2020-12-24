package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day24Test {
    private val dayTwentyFour = Day24()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyFour.partOne(), `is`(10))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyFour.partTwo(), `is`(2208))
    }
}
