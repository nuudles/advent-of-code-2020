package days

@OptIn(ExperimentalUnsignedTypes::class)
class Day14 : Day(14) {
    override fun partOne(): Any {
        val memory = mutableMapOf<Int, ULong>()
        var andMask: ULong = 0xFFFFFFFFu
        var orMask: ULong = 0x0u

        val maskRegex = Regex("""mask = ([X01]{36})""")
        val memRegex = Regex("""mem\[(\d+)\] = (\d+)""")

        inputList
            .forEach { instruction ->
                maskRegex
                    .find(instruction)
                    ?.apply {
                        val (pattern) = destructured
                        andMask = pattern
                            .replace('X', '1')
                            .toULong(2)
                        orMask = pattern
                            .replace('X', '0')
                            .toULong(2)
                    }
                memRegex
                    .find(instruction)
                    ?.apply {
                        val (location, value) = destructured
                        memory[location.toInt()] = (value.toULong() and andMask) or orMask
                    }
            }
        return memory.values.sum()
    }

    override fun partTwo(): Any {
        val memory = mutableMapOf<ULong, ULong>()
        var mask = ""

        val maskRegex = Regex("""mask = ([X01]{36})""")
        val memRegex = Regex("""mem\[(\d+)\] = (\d+)""")

        inputList
            .forEach { instruction ->
                maskRegex
                    .find(instruction)
                    ?.apply {
                        val (pattern) = destructured
                        mask = pattern
                    }
                memRegex
                    .find(instruction)
                    ?.apply {
                        val (location, value) = destructured
                        val xCount = mask.count { it == 'X' }

                        val andMask = mask
                            .replace('0', '1')
                            .replace('X', '0')
                            .toULong(2)

                        for (i in 0UL until 1UL.shl(xCount)) {
                            var orMask = mask
                            i.toString(2).padStart(xCount, '0').forEach {
                                orMask = orMask.replaceFirst('X', it)
                            }

                            memory[location.toULong() and andMask or orMask.toULong(2)] =
                                value.toULong()
                        }
                    }
            }
        return memory.values.sum()
    }
}