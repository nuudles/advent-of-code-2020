package days

class Day17 : Day(17) {
    private fun findNeighbors(
        target: Triple<Int, Int, Int>,
        candidates: Set<Triple<Int, Int, Int>>
    ): Set<Triple<Int, Int, Int>> {
        val neighbors = mutableSetOf<Triple<Int, Int, Int>>()
        for (candidate in candidates.minus(target)) {
            if (
                candidate.first in (target.first - 1)..(target.first + 1) &&
                candidate.second in (target.second - 1)..(target.second + 1) &&
                candidate.third in (target.third - 1)..(target.third + 1)
            ) {
                neighbors.add(candidate)
            }
        }
        return neighbors
    }

    override fun partOne(): Any {
        var activeCubes = mutableSetOf<Triple<Int, Int, Int>>()
        inputList
            .forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    if (c == '#') {
                        activeCubes.add(Triple(x, y, 0))
                    }
                }
            }

        (0 until 6).forEach { _ ->
            val nextCubes = mutableSetOf<Triple<Int, Int, Int>>()
            for (cube in activeCubes) {
                val neighbors = findNeighbors(cube, activeCubes)
                if (neighbors.count() == 2 || neighbors.count() == 3) {
                    nextCubes.add(cube)
                }
            }
            for (x in IntRange(
                (activeCubes
                    .map { it.first }
                    .minOrNull() ?: 0) - 1,
                (activeCubes
                    .map { it.first }
                    .maxOrNull() ?: 0) + 1
            )) {
                for (y in IntRange(
                    (activeCubes
                        .map { it.second }
                        .minOrNull() ?: 0) - 1,
                    (activeCubes
                        .map { it.second }
                        .maxOrNull() ?: 0) + 1
                )) {
                    for (z in IntRange(
                        (activeCubes
                            .map { it.third }
                            .minOrNull() ?: 0) - 1,
                        (activeCubes
                            .map { it.third }
                            .maxOrNull() ?: 0) + 1
                    )) {
                        Triple(x, y, z).let { cube ->
                            if (activeCubes.contains(cube)) {
                                return@let
                            }
                            if (findNeighbors(cube, activeCubes).count() == 3) {
                                nextCubes.add(cube)
                            }
                        }
                    }
                }
            }
            activeCubes = nextCubes
        }
        return activeCubes.count()
    }

    private data class Point4D(
        val x: Int,
        val y: Int,
        val z: Int,
        val w: Int
    )

    private fun findNeighbors(
        target: Point4D,
        candidates: Set<Point4D>
    ): Set<Point4D> {
        val neighbors = mutableSetOf<Point4D>()
        for (candidate in candidates.minus(target)) {
            if (
                candidate.x in (target.x - 1)..(target.x + 1) &&
                candidate.y in (target.y - 1)..(target.y + 1) &&
                candidate.z in (target.z - 1)..(target.z + 1) &&
                candidate.w in (target.w - 1)..(target.w + 1)
            ) {
                neighbors.add(candidate)
            }
        }
        return neighbors
    }

    override fun partTwo(): Any {
        var activeCubes = mutableSetOf<Point4D>()
        inputList
            .forEachIndexed { y, line ->
                line.forEachIndexed { x, c ->
                    if (c == '#') {
                        activeCubes.add(Point4D(x, y, 0, 0))
                    }
                }
            }

        (0 until 6).forEach { _ ->
            val nextCubes = mutableSetOf<Point4D>()
            for (cube in activeCubes) {
                val neighbors = findNeighbors(cube, activeCubes)
                if (neighbors.count() == 2 || neighbors.count() == 3) {
                    nextCubes.add(cube)
                }
            }
            for (x in IntRange(
                (activeCubes
                    .map { it.x }
                    .minOrNull() ?: 0) - 1,
                (activeCubes
                    .map { it.x }
                    .maxOrNull() ?: 0) + 1
            )) {
                for (y in IntRange(
                    (activeCubes
                        .map { it.y }
                        .minOrNull() ?: 0) - 1,
                    (activeCubes
                        .map { it.y }
                        .maxOrNull() ?: 0) + 1
                )) {
                    for (z in IntRange(
                        (activeCubes
                            .map { it.z }
                            .minOrNull() ?: 0) - 1,
                        (activeCubes
                            .map { it.z }
                            .maxOrNull() ?: 0) + 1
                    )) {
                        for (w in IntRange(
                            (activeCubes
                                .map { it.w }
                                .minOrNull() ?: 0) - 1,
                            (activeCubes
                                .map { it.w }
                                .maxOrNull() ?: 0) + 1
                        )) {
                            Point4D(x, y, z, w).let { cube ->
                                if (activeCubes.contains(cube)) {
                                    return@let
                                }
                                if (findNeighbors(cube, activeCubes).count() == 3) {
                                    nextCubes.add(cube)
                                }
                            }
                        }
                    }
                }
            }
            activeCubes = nextCubes
        }
        return activeCubes.count()
    }
}