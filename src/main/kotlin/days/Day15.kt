package days

class Day15 : Day(15) {
    private fun numberForTurn(count: Int): Int {
        val lastTurnMap = mutableMapOf<Int, Int>()
        var lastNumber = 0
        inputString
            .split(",")
            .map { it.toInt() }
            .forEachIndexed { index, number ->
                lastTurnMap[number] = index + 1
                lastNumber = number
            }
        for (turn in (lastTurnMap.count() + 1)..count) {
            val lastTurn = lastTurnMap[lastNumber]
            lastTurnMap[lastNumber] = turn - 1

            lastNumber = lastTurn?.let { turn - 1 - it } ?: 0
        }
        return lastNumber
    }

    override fun partOne() = numberForTurn(2020)

    override fun partTwo() = numberForTurn(30000000)
}