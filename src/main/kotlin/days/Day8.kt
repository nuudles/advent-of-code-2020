package days

class Day8 : Day(8) {
    override fun partOne(): Any {
        val visited = mutableSetOf<Int>()
        var instruction = 0
        var accumulator = 0
        while (!visited.contains(instruction)) {
            visited.add(instruction)
            val components = inputList[instruction].split(" ")
            when (components.first()) {
                "acc" ->
                    accumulator += components.last().toInt()
                "jmp" -> {
                    instruction += components.last().toInt()
                    continue
                }
                else ->
                    Unit
            }
            instruction++
        }
        return accumulator
    }

    private fun runCode(code: List<String>): Int? {
        val visited = mutableSetOf<Int>()
        var instruction = 0
        var accumulator = 0
        while (!visited.contains(instruction)) {
            if (instruction >= code.count()) {
                return accumulator
            }

            visited.add(instruction)
            val components = code[instruction].split(" ")
            when (components.first()) {
                "acc" ->
                    accumulator += components.last().toInt()
                "jmp" -> {
                    instruction += components.last().toInt()
                    continue
                }
                else ->
                    Unit
            }
            instruction++
        }
        return null
    }

    override fun partTwo(): Any {
        val code = inputList.toMutableList()
        for (i in 0 until code.count()) {
            if (code[i].startsWith("jmp")) {
                code[i] = code[i].replace("jmp", "nop")
                runCode(code)?.let { return it }
                code[i] = code[i].replace("nop", "jmp")
            } else if (code[i].startsWith("nop")) {
                code[i] = code[i].replace("nop", "jmp")
                runCode(code)?.let { return it }
                code[i] = code[i].replace("jmp", "nop")
            }
        }
        return -1
    }
}