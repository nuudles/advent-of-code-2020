package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day2Test {
    private val dayTwo = Day2()

    @Test
    fun testParseLine() {
        val (passwordPolicy, password) = dayTwo.parseLine("1-3 a: abcde")
        assertThat(passwordPolicy.min, `is`(1))
        assertThat(passwordPolicy.max, `is`(3))
        assertThat(passwordPolicy.character, `is`("a".single()))
        assertThat(password, `is`("abcde"))
    }

    @Test
    fun testPartOne() {
        assertThat(dayTwo.partOne(), `is`(2))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayTwo.partTwo(), `is`(1))
    }
}