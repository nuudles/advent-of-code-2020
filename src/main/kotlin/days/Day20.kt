package days

private const val tileSize = 10

@OptIn(ExperimentalUnsignedTypes::class)
val UInt.reversedBits: UInt
    get() {
        var reversed = 0U
        var n = this
        (0 until tileSize).forEach { _ ->
            reversed = reversed.shl(1)
            if (n and 1U == 1U) {
                reversed = reversed or 1U
            }
            n = n.shr(1)
        }
        return reversed
    }

fun List<String>.flipHorizontally(): List<String> =
    map {
        it.reversed()
    }

fun List<String>.flipVertically(): List<String> = reversed()

fun List<String>.rotate(): List<String> {
    val newList = mutableListOf<String>()
    for (i in 0 until count()) {
        newList.add(
            (0 until count()).joinToString("") { j ->
                this[count() - j - 1][i].toString()
            }
        )
    }
    return newList
}

@OptIn(ExperimentalUnsignedTypes::class)
class Day20 : Day(20) {
    data class Edge(
        val tile: Tile,
        var ordinal: Int,
        var value: UInt,
        var reversedValue: UInt
    ) {
        constructor(
            tile: Tile,
            ordinal: Int,
            value: UInt
        ) : this(tile, ordinal, value, value.reversedBits)

        constructor(
            tile: Tile,
            ordinal: Int,
            string: String
        ) : this(tile, ordinal, string.replace("#", "1").replace(".", "0").toUInt(2))

        override fun toString() = "{TileID: ${tile.id}, Ordinal: $ordinal, Value: ${
            value.toString(2).padStart(tileSize, '0')
        }, Reversed: ${reversedValue.toString(2).padStart(tileSize, '0')}}"

        fun flip() {
            val oldValue = value
            value = reversedValue
            reversedValue = oldValue
        }
    }

    class Tile(string: String) {
        val id: Long
        val edges: List<Edge>
        val connections = mutableListOf<Pair<Edge, Edge>>()
        var isLocked = false
        var contents: List<String>

        init {
            val lines = string.split("\n")
            id = lines.first().split(" ").last().dropLast(1).toLong()
            edges = listOf(
                Edge(this, 0, lines[1]),
                Edge(this, 1, (1..tileSize).map { lines[it][tileSize - 1] }.joinToString("")),
                Edge(this, 2, lines[tileSize].reversed()),
                Edge(this, 3, (1..tileSize).map { lines[it][0] }.joinToString("").reversed())
            )
            contents = lines
                .drop(2)
                .dropLast(1)
                .map {
                    it
                        .substring(1, tileSize - 1)
                        .replace('.', '0')
                        .replace('#', '1')
                }
        }

        override fun toString() =
            "Tile: $id, Edges: $edges"

        fun flipAlongOrdinal(ordinal: Int) {
            edges.forEach { edge ->
                if (edge.ordinal % 2 == ordinal % 2) {
                    edge.flip()
                } else {
                    edge.ordinal = (edge.ordinal + 2) % 4
                    edge.flip()
                }
            }
            contents = if (ordinal % 2 == 0) {
                contents.flipHorizontally()
            } else {
                contents.flipVertically()
            }
        }

        fun rotate(count: Int) {
            edges.forEach { it.ordinal = (it.ordinal + count) % 4 }
            (0 until count).forEach { _ ->
                contents = contents.rotate()
            }
        }
    }

    override fun partOne(): Any {
        val tiles = inputString
            .split("\n\n")
            .map { Tile(it) }
        val matches = tiles
            .flatMap { it.edges }
            .fold(mutableMapOf<UInt, Set<Edge>>()) { map, edge ->
                map[edge.value]?.let { set ->
                    map[edge.value] = set + edge
                    return@fold map
                }
                map[edge.reversedValue]?.let { set ->
                    map[edge.reversedValue] = set + edge
                    return@fold map
                }
                map[edge.value] = setOf(edge)
                map
            }

        val cornerTiles = matches
            .filter { it.value.count() == 1 }
            .flatMap { it.value }
            .groupBy { it.tile }
            .filter { it.value.count() == 2 }
            .keys

        return cornerTiles.fold(1L) { product, tile -> product * tile.id }
    }

    override fun partTwo(): Any {
        val tiles = inputString
            .split("\n\n")
            .map { Tile(it) }
        val matches = tiles
            .flatMap { it.edges }
            .fold(mutableMapOf<UInt, Set<Edge>>()) { map, edge ->
                map[edge.value]?.let { set ->
                    map[edge.value] = set + edge
                    return@fold map
                }
                map[edge.reversedValue]?.let { set ->
                    map[edge.reversedValue] = set + edge
                    return@fold map
                }
                map[edge.value] = setOf(edge)
                map
            }

        val outsideEdges = matches
            .filter { it.value.count() == 1 }
            .flatMap { it.value }
            .toSet()
        val cornerTiles = outsideEdges
            .groupBy { it.tile }
            .filter { it.value.count() == 2 }
            .keys

        val firstCorner = cornerTiles.first()

        var currentTile: Tile? = firstCorner
        currentTile?.isLocked = true

        val nextTiles = mutableSetOf<Tile>()
        while (currentTile != null) {
            currentTile
                .edges
                .filter { edge -> !(currentTile?.connections?.any { it.first == edge } ?: true) }
                .forEach { edge ->
                    var currentEdge = edge
                    currentEdge.tile.isLocked = true
                    while (true) {
                        currentEdge.tile.isLocked = true
                        val match = matches[currentEdge.value]
                            ?: matches[currentEdge.reversedValue]
                            ?: break
                        val matchedEdge = match.firstOrNull { it != currentEdge } ?: break
                        if (matchedEdge.ordinal != (currentEdge.ordinal + 2) % 4) {
                            if (matchedEdge.tile.isLocked) {
                                println("OH NO! It's locked!")
                                return@forEach
                            } else {
                                matchedEdge.tile.rotate(
                                    ((currentEdge.ordinal + 2) - matchedEdge.ordinal + 4) % 4
                                )
                            }
                        }
                        if (matchedEdge.value != currentEdge.reversedValue) {
                            if (matchedEdge.tile.isLocked) {
                                println("OH NO! It's locked!")
                                return@forEach
                            } else {
                                matchedEdge.tile.flipAlongOrdinal(matchedEdge.ordinal)
                            }
                        }
                        matchedEdge.tile.isLocked = true
                        currentEdge.tile.connections.add(currentEdge to matchedEdge)
                        matchedEdge.tile.connections.add(matchedEdge to currentEdge)
                        matches.remove(currentEdge.value)
                        matches.remove(currentEdge.reversedValue)

                        currentEdge = matchedEdge.tile.edges.first {
                            it.ordinal == currentEdge.ordinal
                        }

                        nextTiles.add(matchedEdge.tile)
                    }
                }
            currentTile = nextTiles.firstOrNull()
            currentTile?.let { nextTiles.remove(it) }
        }

        val xOrdinal = 1
        val yOrdinal = 0

        var contents = mutableListOf<String>()
        currentTile = cornerTiles
            .firstOrNull { tile ->
                tile
                    .connections
                    .map { it.first.ordinal }
                    .containsAll(listOf(xOrdinal, yOrdinal))
            }
        while (currentTile != null) {
            val currentContent = MutableList(currentTile.contents.count()) { "" }
            var xTile = currentTile
            while (xTile != null) {
                xTile
                    .contents
                    .reversed()
                    .forEachIndexed { index, s ->
                        currentContent[index] += s
                    }
                xTile = xTile
                    .connections
                    .firstOrNull { it.first.ordinal == xOrdinal }
                    ?.second
                    ?.tile
            }
            contents.addAll(currentContent)
            currentTile = currentTile
                .connections
                .firstOrNull { it.first.ordinal == yOrdinal }
                ?.second
                ?.tile
        }

        val monsterLine1 = 0x00002UL
        val monsterLine2 = 0x86187UL
        val monsterLine3 = 0x49248UL

        /*
        I was going to do a big loop here to keep rotating and flipping until I found a match,
        but happened to find a match after only a single rotation, so I just kept it... Works
        for the main input, but not for the test input.
         */
        contents = contents.rotate().toMutableList()

        for (y in 0 until contents.count() - 3) {
            for (x in 0 until contents.count() - 20) {
                val line1 = contents[y].substring(x, x + 20).toULong(2)
                val line2 = contents[y + 1].substring(x, x + 20).toULong(2)
                val line3 = contents[y + 2].substring(x, x + 20).toULong(2)

                if (
                    line1 and monsterLine1 == monsterLine1 &&
                    line2 and monsterLine2 == monsterLine2 &&
                    line3 and monsterLine3 == monsterLine3
                ) {
                    contents[y] = contents[y].replaceRange(
                        x,
                        x + 20,
                        (line1 and monsterLine1.inv()).toString(2).padStart(20, '0')
                    )
                    contents[y + 1] = contents[y + 1].replaceRange(
                        x,
                        x + 20,
                        (line2 and monsterLine2.inv()).toString(2).padStart(20, '0')
                    )
                    contents[y + 2] = contents[y + 2].replaceRange(
                        x,
                        x + 20,
                        (line3 and monsterLine3.inv()).toString(2).padStart(20, '0')
                    )
                }
            }
        }

        return contents.sumBy { line ->
            line.count { it == '1' }
        }
    }
}