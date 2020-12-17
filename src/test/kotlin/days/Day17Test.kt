package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day17Test {
    private val daySeventeen = Day17()

    @Test
    fun testPartOne() {
        assertThat(daySeventeen.partOne(), `is`(112))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySeventeen.partTwo(), `is`(848))
    }
}
