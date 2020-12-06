package days

class Day6 : Day(6) {
    override fun partOne() =
        inputString
            .split("\n\n")
            .map { group ->
                group
                    .filter { it != '\n' }
                    .fold(setOf<Char>()) { set, char -> set + char }
                    .count()
            }
            .sum()

    override fun partTwo() =
        inputString
            .split("\n\n")
            .map { group ->
                val peopleCount = group.count { it == '\n' } + 1
                val map = mutableMapOf<Char, Int>()
                group
                    .filter { it != '\n' }
                    .forEach {
                        map[it] = (map[it] ?: 0) + 1
                    }
                map.count { it.value == peopleCount }
            }
            .sum()
}