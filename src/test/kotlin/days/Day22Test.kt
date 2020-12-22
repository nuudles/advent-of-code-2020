package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day22Test {
    private val dayTwentyTwo = Day22()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyTwo.partOne(), `is`(306L))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyTwo.partTwo(), `is`(291))
    }
}
