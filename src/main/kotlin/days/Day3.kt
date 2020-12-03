package days

class Day3 : Day(3) {
    override fun partOne(): Any {
        var x = -3
        return inputList
            .count { line ->
                x += 3
                line[x % line.length] == '#'
            }
    }

    override fun partTwo(): Any {
        val deltas = arrayOf(1 to 1, 3 to 1, 5 to 1, 7 to 1, 1 to 2)
        val treeCount = mutableListOf(0, 0, 0, 0, 0)

        inputList
            .forEachIndexed { index, line ->
                if (index == 0) {
                    return@forEachIndexed
                }
                deltas.forEachIndexed deltaForEach@ { deltaIndex, (x, y) ->
                    if (index % y != 0) {
                        return@deltaForEach
                    }
                    val multiplier = index / y
                    if (line[(x * multiplier) % line.length] == '#') {
                        treeCount[deltaIndex] += 1
                    }
                }
            }
        return treeCount.fold(1L) { product, count -> product * count }
    }
}