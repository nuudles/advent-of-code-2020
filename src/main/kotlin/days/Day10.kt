package days

class Day10 : Day(10) {
    fun calculate(list: List<Int>): Pair<Int, Int> {
        var lastValue = 0
        var oneCount = 0
        var threeCount = 0
        list
            .sorted()
            .forEach { value ->
                when (value) {
                    lastValue + 1 -> oneCount++
                    lastValue + 3 -> threeCount++
                }
                lastValue = value
            }
        return oneCount to threeCount + 1
    }

    override fun partOne() =
        calculate(inputList.map { it.toInt() })
            .let { it.first * it.second }

    // Brute force
    fun combinationCount(current: Int, list: List<Int>): Int {
        if (list.count() == 0) {
            return 1
        } else if (current < list.first() - 3) {
            return 0
        }
        val potentials = mutableListOf<Int>()
        for (i in 0 until list.count()) {
            if (list[i] > current + 3) {
                break
            }
            potentials.add(i)
        }
        return potentials.sumBy { index ->
            combinationCount(
                list[index],
                list.subList(index + 1, list.count())
            )
        }
    }

    fun combinations(list: List<Int>): Long {
        var map = mapOf(0 to 1L)

        val sortedList = list.sorted()
        val target = sortedList.last()
        sortedList
            .forEach { value ->
                val nextMap = mutableMapOf<Int, Long>()
                map.forEach { (mapValue, count) ->
                    if (value - mapValue <= 3) {
                        nextMap[value] = (nextMap[value] ?: 0) + count
                        nextMap[mapValue] = (nextMap[mapValue] ?: 0) + count
                    }
                }
                map = nextMap
            }

        return map[target] ?: 0
    }

    override fun partTwo() =
        combinations(inputList.map { it.toInt() })
}
