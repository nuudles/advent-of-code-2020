package days

class Day16 : Day(16) {
    private data class Field(
        val name: String,
        val ranges: Set<IntRange>
    )

    private fun parseInput(): Triple<Set<Field>, List<Int>, List<List<Int>>> {
        val components = inputString.split("\n\n")
        val fields = components[0]
            .split("\n")
            .fold(setOf<Field>()) { set, line ->
                set + line.split(":").let { fieldComponents ->
                    Field(
                        name = fieldComponents.first(),
                        ranges = fieldComponents
                            .last()
                            .split("or")
                            .fold(setOf()) { rangeSet, ranges ->
                                val test = ranges.trim().split("-").let {
                                    IntRange(it.first().toInt(), it.last().toInt())
                                }
                                rangeSet.plus(element = test)
                            }
                    )
                }
            }
        val myTicket = components[1]
            .split("\n")
            .last()
            .split(",")
            .map { it.toInt() }
        val nearbyTickets = components[2]
            .split("\n")
            .drop(1)
            .map { line -> line.split(",").map { it.toInt() } }
        return Triple(fields, myTicket, nearbyTickets)
    }

    override fun partOne(): Any {
        val (fields, _, nearbyTickets) = parseInput()
        var sum = 0
        nearbyTickets.forEach { ticket ->
            ticket.forEach { number ->
                if (
                    fields.firstOrNull { field ->
                        field.ranges.firstOrNull { range ->
                            range.contains(number)
                        } != null
                    } == null
                ) {
                    sum += number
                }
            }
        }
        return sum
    }

    override fun partTwo(): Any {
        val (fields, myTicket, nearbyTickets) = parseInput()
        val possibleFields = List(fields.count()) { fields.toMutableSet() }
        nearbyTickets
            .filter { ticket ->
                ticket.firstOrNull { number ->
                    fields.firstOrNull { field ->
                        field.ranges.firstOrNull { range ->
                            range.contains(number)
                        } != null
                    } == null
                } == null
            }
            .forEach { ticket ->
                ticket.forEachIndexed { index, number ->
                    if (possibleFields[index].count() == 1) {
                        return@forEachIndexed
                    }
                    possibleFields[index].removeIf { field ->
                        field.ranges.count {
                            it.contains(number)
                        } == 0
                    }
                }
            }
        while (possibleFields.sumBy { it.count() } != possibleFields.count()) {
            val known = possibleFields.filter { it.count() == 1 }.map { it.first() }
            possibleFields
                .filter { it.count() > 1 }
                .forEach { set -> set.removeIf { known.contains(it) } }
        }
        return possibleFields
            .map { it.first() }
            .withIndex()
            .filter { it.value.name.startsWith("departure") }
            .fold(1L) { product, field ->
                product * myTicket[field.index]
            }
    }
}
