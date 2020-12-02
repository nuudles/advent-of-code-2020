package days

class Day2 : Day(2) {
    // region - Data Classes -
    data class PasswordPolicy(
        val min: Int,
        val max: Int,
        val character: Char
    )
    // endregion

    // region - Extensions -
    private fun PasswordPolicy.isValid(password: String): Boolean {
        val count = password.count { it == character }
        return count in min..max
    }

    private fun PasswordPolicy.isValid2(password: String): Boolean {
        return (password[min - 1] == character || password[max - 1] == character) &&
                password[min - 1] != password[max - 1]
    }
    // endregion

    // region - Helper Functions -
    fun parseLine(line: String): Pair<PasswordPolicy, String> {
        val components = line.split("-", ":", " ")
        return Pair(
            PasswordPolicy(
                min = components[0].toInt(),
                max = components[1].toInt(),
                character = components[2].single()
            ),
            components[4]
        )
    }
    // endregion

    // region - Day Overridden Functions -
    override fun partOne() =
        inputList
            .map { parseLine(it) }
            .count { it.first.isValid(it.second) }

    override fun partTwo() =
        inputList
            .map { parseLine(it) }
            .count { it.first.isValid2(it.second) }
    // endregion
}