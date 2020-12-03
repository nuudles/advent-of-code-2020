package days

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test

class Day3Test {
    private val dayThree = Day3()

    @Test
    fun testPartOne() {
        MatcherAssert.assertThat(dayThree.partOne(), CoreMatchers.`is`(7))
    }

    @Test
    fun testPartTwo() {
        MatcherAssert.assertThat(dayThree.partTwo(), CoreMatchers.`is`(336L))
    }
}