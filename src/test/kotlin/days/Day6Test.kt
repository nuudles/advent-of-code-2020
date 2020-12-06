package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day6Test {
    private val daySix = Day6()

    @Test
    fun testPartOne() {
        assertThat(daySix.partOne(), `is`(11))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySix.partTwo(), `is`(6))
    }
}
