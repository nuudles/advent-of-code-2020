package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day19Test {
    private val dayNineteen = Day19()

    @Test
    fun testPartOne() {
        assertThat(dayNineteen.partOne(), `is`(3))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayNineteen.partTwo(), `is`(12))
    }
}
