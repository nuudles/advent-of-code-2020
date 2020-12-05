package days

class Day5 : Day(5) {
    fun seatId(row: Int, col: Int) = row * 8 + col

    fun seat(pattern: String): Pair<Int, Int> {
        var rows = 0..127
        var cols = 0..7
        pattern
            .forEach { char ->
                when (char) {
                    'F' -> rows = rows.first..(rows.last - (rows.last - rows.first + 1) / 2)
                    'B' -> rows = (rows.first + (rows.last - rows.first + 1) / 2)..rows.last
                    'L' -> cols = cols.first..(cols.last - (cols.last - cols.first + 1) / 2)
                    'R' -> cols = (cols.first + (cols.last - cols.first + 1) / 2)..cols.last
                    else -> Unit
                }
            }
        return rows.last to cols.last
    }

    override fun partOne() =
        inputList
            .map { pattern -> seat(pattern).let { seatId(it.first, it.second) } }
            .maxOrNull() ?: 0

    override fun partTwo() =
        inputList
            .map { pattern ->
                seat(pattern).let { seatId(it.first, it.second) }
            }
            .fold(setOf<Int>()) { set, id -> set + id }
            .let { set ->
                val min = set.minOrNull() ?: 0
                val max = set.maxOrNull() ?: 0
                (min..max)
                    .fold(setOf<Int>()) { rangeSet, i -> rangeSet + i }
                    .subtract(set)
                    .first()
            }
}