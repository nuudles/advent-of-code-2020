package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day5Test {
    private val dayFive = Day5()

    @Test
    fun testSeat() {
        dayFive.seat("BFFFBBFRRR").let { seat ->
            assertThat(seat.first, `is`(70))
            assertThat(seat.second, `is`(7))
        }
        dayFive.seat("FFFBBBFRRR").let { seat ->
            assertThat(seat.first, `is`(14))
            assertThat(seat.second, `is`(7))
        }
        dayFive.seat("BBFFBBFRLL").let { seat ->
            assertThat(seat.first, `is`(102))
            assertThat(seat.second, `is`(4))
        }
    }

    @Test
    fun testPartOne() {
        assertThat(dayFive.partOne(), `is`(Unit))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFive.partTwo(), `is`(Unit))
    }
}