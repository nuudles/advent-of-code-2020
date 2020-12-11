package days

class Day11 : Day(11) {
    private val width: Int by lazy { inputList.first().count() }
    private val height: Int by lazy { inputList.count() }

    private var board = inputList

    private fun printBoard() {
        println(board.joinToString("\n"))
        print("\n")
    }

    private fun isOccupied(x: Int, y: Int): Boolean {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return false
        }
        return when (board[y][x]) {
            'L' -> false
            '.' -> false
            '#' -> true
            else -> false
        }
    }

    private fun shouldFlip(x: Int, y: Int): Boolean {
        when (board[y][x]) {
            'L' -> {
                for (deltaX in -1..1) {
                    for (deltaY in -1..1) {
                        if (deltaX == 0 && deltaY == 0) {
                            continue
                        }
                        if (isOccupied(x + deltaX, y + deltaY)) {
                            return false
                        }
                    }
                }
                return true
            }
            '#' -> {
                var occupiedCount = 0
                for (deltaX in -1..1) {
                    for (deltaY in -1..1) {
                        if (deltaX == 0 && deltaY == 0) {
                            continue
                        }
                        if (isOccupied(x + deltaX, y + deltaY)) {
                            occupiedCount++
                            if (occupiedCount >= 4) {
                                return true
                            }
                        }
                    }
                }
                return false
            }
        }
        return false
    }

    override fun partOne(): Any {
        var didChange = true
        while (didChange) {
//            printBoard()
            didChange = false

            val newBoard = mutableListOf<String>()
            for (y in 0 until height) {
                var line = ""
                for (x in 0 until width) {
                    if (shouldFlip(x, y)) {
                        line += when (board[y][x]) {
                            'L' -> '#'
                            '#' -> 'L'
                            else -> '?'
                        }
                        didChange = true
                    } else {
                        line += board[y][x]
                    }
                }
                newBoard.add(line)
            }
            board = newBoard
        }
        return board.sumBy { line -> line.count { it == '#' } }
    }

    private fun canSeeOccupiedSeat(x: Int, y: Int, deltaX: Int, deltaY: Int): Boolean {
        if (x !in 0 until width || y !in 0 until height) {
            return false
        }

        return when (board[y][x]) {
            '.' -> canSeeOccupiedSeat(x + deltaX, y + deltaY, deltaX, deltaY)
            'L' -> false
            '#' -> true
            else -> false
        }
    }

    private fun shouldFlip2(x: Int, y: Int): Boolean {
        when (board[y][x]) {
            'L' -> {
                for (deltaX in -1..1) {
                    for (deltaY in -1..1) {
                        if (deltaX == 0 && deltaY == 0) {
                            continue
                        }
                        if (canSeeOccupiedSeat(x + deltaX, y + deltaY, deltaX, deltaY)) {
                            return false
                        }
                    }
                }
                return true
            }
            '#' -> {
                var occupiedCount = 0
                for (deltaX in -1..1) {
                    for (deltaY in -1..1) {
                        if (deltaX == 0 && deltaY == 0) {
                            continue
                        }
                        if (x == 6 && y == 0) {
                            println(board[y][x])
                        }
                        if (canSeeOccupiedSeat(x + deltaX, y + deltaY, deltaX, deltaY)) {
                            occupiedCount++
                            if (occupiedCount >= 5) {
                                return true
                            }
                        }
                    }
                }
                return false
            }
        }
        return false
    }

    override fun partTwo(): Any {
        var didChange = true
        while (didChange) {
            printBoard()
            didChange = false

            val newBoard = mutableListOf<String>()
            for (y in 0 until height) {
                var line = ""
                for (x in 0 until width) {
                    if (shouldFlip2(x, y)) {
                        line += when (board[y][x]) {
                            'L' -> '#'
                            '#' -> 'L'
                            else -> '?'
                        }
                        didChange = true
                    } else {
                        line += board[y][x]
                    }
                }
                newBoard.add(line)
            }
            board = newBoard
        }
        return board.sumBy { line -> line.count { it == '#' } }
    }
}
