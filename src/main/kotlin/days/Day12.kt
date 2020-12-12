package days

import java.awt.Point
import kotlin.math.abs

class Day12 : Day(12) {
    companion object {
        private val deltas = listOf(
            1 to 0,
            0 to 1,
            -1 to 0,
            0 to -1
        )
    }

    override fun partOne(): Any {
        var direction = 0
        val position = Point(0, 0)
        inputList
            .forEach { row ->
                val value = row.drop(1).toInt()
                when (row[0]) {
                    'F' -> {
                        position.x += deltas[direction].first * value
                        position.y += deltas[direction].second * value
                    }
                    'N' ->
                        position.y -= value
                    'S' ->
                        position.y += value
                    'E' ->
                        position.x += value
                    'W' ->
                        position.x -= value
                    'L' ->
                        direction -= (value / 90)
                    'R' ->
                        direction += value / 90
                }
                while (direction < 0) {
                    direction += deltas.count()
                }
                direction %= deltas.count()
            }
        return abs(position.x) + abs(position.y)
    }

    private fun rotate(waypoint: Point, direction: Int, count: Int) {
        (0 until count).forEach { _ ->
            val (x, y) = waypoint.x to waypoint.y
            if (direction < 0) {
                waypoint.x = y
                waypoint.y = -x
            } else {
                waypoint.x = -y
                waypoint.y = x
            }
        }
    }

    override fun partTwo(): Any {
        val position = Point(0, 0)
        val waypoint = Point(10, -1)
        inputList
            .forEach { row ->
                val value = row.drop(1).toInt()
                when (row[0]) {
                    'F' -> {
                        position.x += waypoint.x * value
                        position.y += waypoint.y * value
                    }
                    'N' ->
                        waypoint.y -= value
                    'S' ->
                        waypoint.y += value
                    'E' ->
                        waypoint.x += value
                    'W' ->
                        waypoint.x -= value
                    'L' ->
                        rotate(waypoint, -1, value / 90)
                    'R' ->
                        rotate(waypoint, 1, value / 90)
                }
            }
        return abs(position.x) + abs(position.y)
    }
}