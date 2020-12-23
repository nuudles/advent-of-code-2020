package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day23Test {
    private val dayTwentyThree = Day23()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyThree.partOne(), `is`("67384529"))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyThree.partTwo(), `is`(149245887792L))
    }
}
