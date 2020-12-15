package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day15Test {
    private val dayFifteen = Day15()

    @Test
    fun testPartOne() {
        assertThat(dayFifteen.partOne(), `is`(436))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFifteen.partTwo(), `is`(175594))
    }
}
