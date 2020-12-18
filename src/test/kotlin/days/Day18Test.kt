package days

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class Day18Test {
    private val dayEighteen = Day18()

    @Test
    fun testExpressions() {
        Day18.Token.addPrecedence(Day18.Token.tokensFromString("1 + (2 * 3) + (4 * (5 + 6))"))

        assertThat(
            Day18.Expression.fromTokens(
                Day18.Token.tokensFromString("1 + 2 * 3 + 4 * 5 + 6")
            ).evaluate(),
            `is`(71L)
        )
        assertThat(
            Day18.Expression.fromTokens(
                Day18.Token.tokensFromString("1 + (2 * 3) + (4 * (5 + 6))")
            ).evaluate(),
            `is`(51L)
        )
        assertThat(
            Day18.Expression.fromTokens(
                Day18.Token.addPrecedence(
                    Day18.Token.tokensFromString("1 + 2 * 3 + 4 * 5 + 6")
                )
            ).evaluate(),
            `is`(231L)
        )
        assertThat(
            Day18.Expression.fromTokens(
                Day18.Token.addPrecedence(
                    Day18.Token.tokensFromString("1 + (2 * 3) + (4 * (5 + 6))")
                )
            ).evaluate(),
            `is`(51L)
        )
        assertThat(
            Day18.Expression.fromTokens(
                Day18.Token.addPrecedence(
                    Day18.Token.tokensFromString("2 * 3 + (4 * 5)")
                )
            ).evaluate(),
            `is`(46L)
        )
    }

    @Test
    fun testPartOne() {
        assertThat(dayEighteen.partOne(), `is`(0L))
    }

    @Test
    fun testPartTwo() {
        assertThat(dayEighteen.partTwo(), `is`(0L))
    }
}
