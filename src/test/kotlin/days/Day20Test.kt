package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day20Test {
    private val dayTwenty = Day20()

    @Test
    fun testPartOne() {
        assertThat(dayTwenty.partOne(), `is`(20899048083289L))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwenty.partTwo(), `is`(Unit))
    }
}
