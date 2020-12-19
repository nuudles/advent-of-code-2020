package days

class Day19 : Day(19) {
    sealed class Rule {
        data class Letter(val char: String) : Rule()
        data class RuleSet(val rules: List<List<Int>>) : Rule()
    }

    private val rules: Map<Int, Rule> by lazy {
        val rules = mutableMapOf<Int, Rule>()
        inputString
            .split("\n\n")
            .first()
            .split("\n")
            .forEach { line ->
                line
                    .split(":")
                    .let { components ->
                        rules[components[0].toInt()] = when {
                            components[1] == " \"a\"" -> Rule.Letter("a")
                            components[1] == " \"b\"" -> Rule.Letter("b")
                            else -> Rule.RuleSet(
                                components[1]
                                    .trim()
                                    .split("|")
                                    .map { ruleList ->
                                        ruleList
                                            .trim()
                                            .split(" ")
                                            .map { it.toInt() }
                                    }
                            )
                        }
                    }
            }
        return@lazy rules
    }

    private fun possibleStrings(ruleNumber: Int): Set<String> {
        return when (val rule = rules[ruleNumber] ?: return setOf()) {
            is Rule.Letter ->
                setOf(rule.char)
            is Rule.RuleSet ->
                rule
                    .rules
                    .fold(setOf()) { set, subRules ->
                        set + subRules
                            .map { possibleStrings(it) }
                            .fold(setOf("")) { subset, possibleStrings ->
                                val newSet = mutableSetOf<String>()
                                possibleStrings.forEach { possibleString ->
                                    subset.forEach {
                                        newSet.add(it + possibleString)
                                    }
                                }
                                newSet
                            }
                    }
        }
    }

    override fun partOne(): Any {
        val possibleStrings = possibleStrings(0)
        return inputString
            .split("\n\n")
            .last()
            .split("\n")
            .count { possibleStrings.contains(it) }
    }

    override fun partTwo(): Any {
        val rule42Set = possibleStrings(42)
        val rule31Set = possibleStrings(31)

        val rule42Pattern = rule42Set.joinToString("|")
        val rule31Pattern = rule31Set.joinToString("|")

        val regex = Regex("""^(?:$rule42Pattern)+(?:${
            (1..11)
                .joinToString("|") { count ->
                    "(?:$rule42Pattern){$count}(?:$rule31Pattern){$count}$"
                }
        })$"""
        )
        return inputString
            .split("\n\n")
            .last()
            .split("\n")
            .count { regex.matches(it) }
    }
}