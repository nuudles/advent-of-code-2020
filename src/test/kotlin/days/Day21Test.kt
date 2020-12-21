package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day21Test {
    private val dayTwentyOne = Day21()

    @Test
    fun testPartOne() {
        assertThat(dayTwentyOne.partOne(), `is`(5))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwentyOne.partTwo(), `is`("mxmxvkd,sqjhc,fvjkl"))
    }
}
