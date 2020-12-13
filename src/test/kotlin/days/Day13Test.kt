package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day13Test {
    private val dayThirteen = Day13()

    @Test
    fun testPartOne() {
        assertThat(dayThirteen.partOne(), `is`(295))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayThirteen.partTwo(), `is`(1068781L))
    }
}
