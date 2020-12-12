package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day12Test {
    private val dayTwelve = Day12()

    @Test
    fun testPartOne() {
        assertThat(dayTwelve.partOne(), `is`(25))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwelve.partTwo(), `is`(286))
    }
}
