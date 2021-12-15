package dev.schlaubi.aoc.day_fourteen

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val inputLines = readLines()

        val template = inputLines.first()
        val pairs = inputLines.drop(2).associate {
            it.take(2) to it.takeLast(1).first()
        }

        val recipe = template.step(10, pairs)
        val grouping = recipe.groupingBy { it }
        val sortedCounts = grouping
                .eachCount()
                .toList()
                .sortedBy { (_, count) -> count }

        val (_, min) = sortedCounts.first()
        val (_, max) = sortedCounts.last()

        context.output(max - min)
    }
}

private object IntCharComparator : Comparator<Char> {
    override fun compare(o1: Char, o2: Char): Int = o1.digitToInt().compareTo(o2.digitToInt())
}

private fun String.step(times: Int, pairs: Map<String, Char>) = (0 until times)
        .fold(asIterable()) { acc, _ ->
            acc.zipWithNext().flatMap { (a, b) ->
                val key = "$a$b"
                listOf(a, pairs[key]!!)
            } + acc.last()
        }
