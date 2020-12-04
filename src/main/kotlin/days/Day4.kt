package days

class Day4 : Day(4) {
    private fun getPassports(): List<Map<String, String>> =
        inputString
            .split("\n\n")
            .map { passport ->
                passport
                    .split(" ", "\n")
                    .fold(mutableMapOf()) { map, components ->
                        components
                            .split(":")
                            .let { map[it.first().toString()] = it.last().toString() }
                        map
                    }
            }

    fun isValid(key: String, value: String): Boolean =
        when (key) {
            "byr" ->
                value.length == 4 && value.toIntOrNull()?.let { it in 1920..2002 } ?: false
            "iyr" ->
                value.length == 4 && value.toIntOrNull()?.let { it in 2010..2020 } ?: false
            "eyr" ->
                value.length == 4 && value.toIntOrNull()?.let { it in 2020..2030 } ?: false
            "hgt" ->
                Regex("""(\d+)(cm|in)""")
                    .find(value)
                    ?.let { result ->
                        val (number, unit) = result.destructured
                        return when (unit) {
                            "cm" -> number.toInt() in 150..193
                            "in" -> number.toInt() in 59..76
                            else -> false
                        }
                    } ?: false
            "hcl" ->
                Regex("""#[0-9a-f]{6}""")
                    .matches(value)
            "ecl" ->
                arrayOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
            "pid" ->
                Regex("""\d{9}""")
                    .matches(value)
            else -> true
        }

    override fun partOne(): Any {
        return getPassports()
            .count { passport ->
                passport.count { requiredFields.contains(it.key) } == requiredFields.size
            }
    }

    override fun partTwo(): Any {
        return getPassports()
            .count { passport ->
                requiredFields
                    .count { field ->
                        passport[field]?.let { isValid(field, it) } ?: false
                    } == requiredFields.size
            }
    }

    companion object {
        private val requiredFields =
            arrayOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    }
}
