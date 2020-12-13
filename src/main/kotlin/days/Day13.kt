package days

import kotlin.math.ceil

class Day13 : Day(13) {
    override fun partOne(): Any {
        val target = inputList.first().toFloat()
        return inputList
            .last()
            .split(",")
            .filter { it != "x" }
            .map { it.toInt() }
            .minByOrNull { multiplier ->
                ceil(target / multiplier.toFloat()) * target - target
            }
            ?.let {
                (it * (ceil(target / it.toFloat()) * it - target)).toInt()
            } ?: Unit
    }

    override fun partTwo(): Any =
        inputList
            .last()
            .split(",")
            .withIndex()
            .filter { it.value != "x" }
            .map { IndexedValue(it.index, it.value.toLong()) }
            .let { list ->
                var ts = 1L
                var stepCount = 0
                var stepper = 1L
                while (true) {
                    println(ts)
                    list
                        .map { it.value to ((ts + it.index) % it.value == 0L) }
                        .filter { it.second }
                        .let { matches ->
                            if (matches.count() == list.count()) {
                                return ts
                            } else if (matches.count() > stepCount) {
                                stepper = matches.fold(1L) { product, entry ->
                                    product * entry.first
                                }
                                stepCount = matches.count()
                            }
                        }
                    ts += stepper
                }
            }
            /*
            // Brute Force
            .let { list ->
                var ts = 100000000000000L
                while (true) {
                    if (list.sumBy { ((ts + it.index) % it.value).toInt() } == 0) {
                        return@let ts
                    }
                    ts++
                }
            }
             */
}