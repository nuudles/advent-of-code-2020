package days

class Day24 : Day(24) {
    private val blackTiles: Set<Pair<Int, Int>>

    init {
        val regex = Regex("e|se|sw|w|nw|ne")
        blackTiles = inputList
            .fold(mutableSetOf()) { set, line ->
                var (x, y) = 0 to 0
                regex
                    .findAll(line)
                    .forEach { match ->
                        when (match.value) {
                            "e" -> x++
                            "w" -> x--
                            "ne" -> y--
                            "se" -> {
                                x++
                                y++
                            }
                            "nw" -> {
                                x--
                                y--
                            }
                            "sw" -> y++
                        }
                    }
                if (!set.remove(x to y)) {
                    set.add(x to y)
                }
                set
            }
    }

    override fun partOne() = blackTiles.count()

    val Pair<Int, Int>.neighbors: Set<Pair<Int, Int>>
        get() = setOf(
            first + 1 to second,
            first - 1 to second,
            first to second - 1,
            first + 1 to second + 1,
            first - 1 to second - 1,
            first to second + 1
        )

    override fun partTwo(): Any {
        var currentTiles = blackTiles
        (1..100).forEach { _ ->
            val newTiles = mutableSetOf<Pair<Int, Int>>()
            val checkedTiles = mutableSetOf<Pair<Int, Int>>()

            currentTiles
                .forEach { tile ->
                    val neighbors = tile.neighbors
                    val flippedNeighbors = neighbors.intersect(currentTiles)
                    val unflippedNeighbors = neighbors.subtract(flippedNeighbors)
                    flippedNeighbors.count().let {
                        if (it != 0 && it <= 2) {
                            newTiles.add(tile)
                        }
                    }
                    unflippedNeighbors.forEach { neighbor ->
                        if (!checkedTiles.contains(neighbor)) {
                            if (neighbor.neighbors.intersect(currentTiles).count() == 2) {
                                newTiles.add(neighbor)
                            }
                            checkedTiles.add(neighbor)
                        }
                    }
                }

            currentTiles = newTiles
        }
        return currentTiles.count()
    }
}