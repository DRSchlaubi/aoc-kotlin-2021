package dev.schlaubi.aoc.day_nine

import dev.schlaubi.aoc.readLines

fun List<List<Int>>.findLowPoints(): Sequence<Point> {
    return asSequence()
            .flatMapIndexed { row, ints ->
                ints.asSequence()
                        .mapIndexed { column, i ->
                            val up = getOrNull(row - 1)?.getOrNull(column)
                            val left = ints.getOrNull(column - 1)
                                    ?: getOrNull(row + 1)?.getOrNull(column + 1)
                            val down = getOrNull(row + 1)?.getOrNull(column)
                            val right = ints.getOrNull(column + 1)
                                    ?: getOrNull(row - 1)?.getOrNull(column - 1)

                            val isLowPoint = i.compareSafe(up) < 0
                                    && i.compareSafe(left) < 0
                                    && i.compareSafe(down) < 0
                                    && i.compareSafe(right) < 0
                            Point(row, column, i, isLowPoint)
                        }
            }
            .filter { (_, _, _, lowPoint) -> lowPoint }
}

fun Any.parseHeights() = readLines().map { row -> row.asIterable().map { it.digitToInt() } }

private fun <T : Comparable<T>> T.compareSafe(other: T?) = other?.let { compareTo(it) } ?: -1

data class Point(val x: Int, val y: Int, val value: Int, val isLow: Boolean)
