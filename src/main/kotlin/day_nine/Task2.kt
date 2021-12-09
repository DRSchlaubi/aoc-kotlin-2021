package dev.schlaubi.aoc.day_nine

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val heights = parseHeights()
        val lowPoints = heights.findLowPoints().toList()


        val output = lowPoints
                .asSequence()
                .map { heights.calcBasinSize(it.x to it.y) }
                .sortedDescending()
                .take(3)
                .reduce { acc, i -> acc * i }

        context.output(output)
    }
}

fun List<List<Int>>.calcBasinSize(lowPoint: Pair<Int, Int>): Int {
    val toCheck = mutableSetOf(lowPoint)
    val checked = mutableSetOf<Pair<Int, Int>>()
    while (toCheck.isNotEmpty()) {
        val candidate = toCheck.first()
        toCheck.remove(candidate)
        checked.add(candidate)
        toCheck.addAll(higherPoints(candidate))
    }
    return checked.size
}

fun List<List<Int>>.higherPoints(lowPoint: Pair<Int, Int>): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()
    if ((lowPoint.first > 0)) {
        val candidate = lowPoint.first - 1 to lowPoint.second
        if (this[candidate.first][candidate.second] < 9 && this[candidate.first][candidate.second] > this[lowPoint.first][lowPoint.second])
            result.add(candidate)
    }
    if (lowPoint.second > 0) {
        val candidate = lowPoint.first to lowPoint.second - 1
        if (this[candidate.first][candidate.second] < 9 && this[candidate.first][candidate.second] > this[lowPoint.first][lowPoint.second])
            result.add(candidate)
    }
    if (lowPoint.second < this[0].size - 1) {
        val candidate = lowPoint.first to lowPoint.second + 1
        if (this[candidate.first][candidate.second] < 9 && this[candidate.first][candidate.second] > this[lowPoint.first][lowPoint.second])
            result.add(candidate)
    }
    if (lowPoint.first < this.size - 1) {
        val candidate = lowPoint.first + 1 to lowPoint.second
        if (this[candidate.first][candidate.second] < 9 && this[candidate.first][candidate.second] > this[lowPoint.first][lowPoint.second])
            result.add(candidate)
    }
    return result.toList()
}
