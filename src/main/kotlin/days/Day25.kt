package days

class Day25 : Day(25) {
    private fun step(value: Long, subject: Long) = (value * subject) % 20201227L

    override fun partOne(): Any {
        val (cardPublicKey, doorPublicKey) = inputList.run { first().toLong() to last().toLong() }
        var cardLoopSize: Long? = null
        var doorLoopSize: Long? = null

        var value = 1L
        var loop = 1L
        while (cardLoopSize == null || doorLoopSize == null) {
            value = step(value, 7L)
            if (cardPublicKey == value) {
                cardLoopSize = loop
            }
            if (doorPublicKey == value) {
                doorLoopSize = loop
            }
            loop++
        }
        value = 1L
        (0 until cardLoopSize).forEach { _ ->
            value = step(value, doorPublicKey)
        }
        return value
    }

    override fun partTwo(): Any {
        return Unit
    }
}