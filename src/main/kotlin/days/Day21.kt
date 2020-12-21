package days

class Day21 : Day(21) {
    private val allergenMapping: Map<String, String>
    private val allIngredients: List<String>

    init {
        val mapping = mutableMapOf<String, Set<String>>()
        val allIngredients = mutableListOf<String>()
        inputList
            .forEach { line ->
                val components = line.split(" (contains ")
                allIngredients.addAll(components.first().split(" "))
                val ingredients = components.first().split(" ").toSet()
                components
                    .last()
                    .dropLast(1)
                    .split(", ")
                    .forEach { allergen ->
                        mapping[allergen] = mapping[allergen]?.intersect(ingredients) ?: ingredients
                    }
            }

        while (mapping.values.sumBy { it.count() } != mapping.count()) {
            mapping
                .filter { it.value.count() == 1 }
                .map { it.value.first() }
                .forEach { found ->
                    mapping.forEach { (key, set) ->
                        if (set.count() > 1 && set.contains(found)) {
                            mapping[key] = set.minus(found)
                        }
                    }
                }
        }

        allergenMapping = mapping.mapValues { it.value.first() }
        this.allIngredients = allIngredients
    }

    override fun partOne(): Any {
        val allergens = allergenMapping.values.toSet()
        return allIngredients.count { !allergens.contains(it) }
    }

    override fun partTwo() =
        allergenMapping
            .map { it.key to it.value }
            .sortedBy { it.first }
            .joinToString(",") { it.second }
}
