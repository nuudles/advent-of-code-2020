package days

class Day7 : Day(7) {
    data class Rule(
        val container: String,
        val contains: List<Pair<String, Int>>
    )

    fun parseRules(): List<Rule> =
        inputList
            .map { line ->
                line
                    .split("contain")
                    .let { components ->
                        Rule(
                            container = components[0].replace(" bags ", ""),
                            contains = components[1]
                                .split(",")
                                .mapNotNull { contains ->
                                    Regex("""(\d) (.*) bag[s]*""")
                                        .find(contains)
                                        ?.let { result ->
                                            val (count, descriptor) = result.destructured
                                            descriptor to count.toInt()
                                        }
                                }
                        )
                    }
            }

    override fun partOne(): Any {
        val rules = parseRules()
        val containedBy = mutableMapOf<String, Set<String>>()
        rules
            .forEach { rule ->
                rule.contains.forEach {
                    containedBy[it.first] = (containedBy[it.first] ?: setOf()) + rule.container
                }
            }

        val visited = mutableSetOf<String>()
        fun containCount(container: String): Int {
            if (visited.contains(container)) {
                return 0
            }

            visited.add(container)
            return (containedBy[container]?.sumBy { containCount(it) } ?: 0) + 1
        }
        return containCount("shiny gold") - 1
    }

    override fun partTwo(): Any {
        val contains = parseRules()
            .fold(mutableMapOf<String, List<Pair<String, Int>>>()) { map, rule ->
                map[rule.container] = rule.contains
                map
            }

        fun containCount(container: String): Int {
            return (
                    contains[container]
                        ?.map { it.second * containCount(it.first) }
                        ?.sum()
                        ?: 0
                    ) + 1
        }
        return containCount("shiny gold") - 1
    }
}