package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day25Test {
    private val dayTwentyFive = Day25()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyFive.partOne(), `is`(14897079L))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyFive.partTwo(), `is`(Unit))
    }
}
