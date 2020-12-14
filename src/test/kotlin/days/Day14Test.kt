package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

@OptIn(ExperimentalUnsignedTypes::class)
class Day14Test {
    private val dayFourteen = Day14()

    @Test
    fun testPartOne() {
        assertThat(dayFourteen.partOne(), `is`(165UL))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFourteen.partTwo(), `is`(208UL))
    }
}
