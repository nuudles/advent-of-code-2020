package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day4Test {
    private val dayFour = Day4()

    @Test
    fun testPartOne() {
        assertThat(dayFour.partOne(), `is`(2))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayFour.partTwo(), `is`(2))
    }

    @Test
    fun testIsValid() {
        assertThat(dayFour.isValid("byr", "2002"), `is`(true))
        assertThat(dayFour.isValid("byr", "2003"), `is`(false))
        assertThat(dayFour.isValid("byr", "a003"), `is`(false))
        assertThat(dayFour.isValid("byr", "12342"), `is`(false))

        assertThat(dayFour.isValid("hgt", "60in"), `is`(true))
        assertThat(dayFour.isValid("hgt", "190cm"), `is`(true))
        assertThat(dayFour.isValid("hgt", "190in"), `is`(false))
        assertThat(dayFour.isValid("hgt", "190"), `is`(false))

        assertThat(dayFour.isValid("hcl", "#123abc"), `is`(true))
        assertThat(dayFour.isValid("hcl", "#123abz"), `is`(false))
        assertThat(dayFour.isValid("hcl", "123abc"), `is`(false))

        assertThat(dayFour.isValid("ecl", "brn"), `is`(true))
        assertThat(dayFour.isValid("ecl", "wat"), `is`(false))

        assertThat(dayFour.isValid("pid", "000000001"), `is`(true))
        assertThat(dayFour.isValid("pid", "0123456789"), `is`(false))
    }
}
