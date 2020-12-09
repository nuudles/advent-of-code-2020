package days

class Day9 : Day(9) {
    companion object {
        var preambleSize = 25
        var partTwoTarget = 542529149L
    }

    private val inputNumbers by lazy { inputList.map { it.toLong() } }

    private fun isValid(index: Int): Boolean {
        if (index < preambleSize) {
            return true
        }
        val target = inputNumbers[index]
        val set = mutableSetOf<Long>()
        inputNumbers
            .subList(index - preambleSize, index)
            .forEach { number ->
                if (set.contains(number)) {
                    return true
                }
                set.add(target - number)
            }
        return false
    }

    override fun partOne(): Any {
        for (i in 0 until inputNumbers.count()) {
            if (!isValid(i)) {
                return inputNumbers[i]
            }
        }
        return Unit
    }

    override fun partTwo(): Any {
        for (i in 0 until inputNumbers.count()) {
            for (j in 0 until i) {
                val subList = inputNumbers.subList(j, i)
                if (subList.sum() == partTwoTarget) {
                    return (subList.minOrNull() ?: 0) + (subList.maxOrNull() ?: 0)
                }
            }
        }
        return Unit
    }
}