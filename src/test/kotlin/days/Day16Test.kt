package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day16Test {
    private val daySixteen = Day16()

    @Test
    fun testPartOne() {
        assertThat(daySixteen.partOne(), `is`(71))
    }

    @Test
    fun testPartTwo() {
        assertThat(daySixteen.partTwo(), `is`(Unit))
    }
}
