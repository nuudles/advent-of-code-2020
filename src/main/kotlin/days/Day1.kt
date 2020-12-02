package days

class Day1 : Day(1) {

    override fun partOne(): Any {
        val set = HashSet<Int>()
        return inputList
            .mapNotNull { it.toIntOrNull() }
            .forEach { entry ->
                if (set.contains(2020 - entry)) {
                    return entry * (2020 - entry)
                }
                set.add(entry)
            }
    }

    override fun partTwo(): Any {
        val entries = inputList.mapNotNull { it.toIntOrNull() }
        entries.forEachIndexed { index, entry ->
            val set = HashSet<Int>()
            entries.forEachIndexed forEachIndexed2@ { index2, entry2 ->
                if (index == index2) return@forEachIndexed2
                if (set.contains(2020 - entry - entry2)) {
                    return entry * entry2 * (2020 - entry - entry2)
                }
                set.add(entry2)
            }
        }
        return Unit
    }
}
