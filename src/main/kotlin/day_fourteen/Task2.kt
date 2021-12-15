package dev.schlaubi.aoc.day_fourteen

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val inputLines = readLines()

        val map = inputLines.drop(1).associate { it.take(2) to it.takeLast(1) }
        var pairs = mutableMapOf<String, Long>()
        val chars = mutableMapOf<Char, Long>()

        inputLines.first().zipWithNext().forEach { (a,b) -> pairs.increment("$a$b") }

        repeat(40) {
            val temp = mutableMapOf<String, Long>()

            pairs.forEach { (k, v) ->
                temp.increment(k[0] + map[k]!!, v)
                temp.increment(map[k]!! + k[1], v)
            }

            pairs = temp
        }

        pairs.forEach { (k, v) -> chars.increment(k[0], v) }
        chars.increment(inputLines.first().last())

        val sorted = chars.toList().sortedBy { (_, amount) -> amount }
        val (_, min) = sorted.first()
        val (_, max) = sorted.last()

        context.output(max - min)
    }

    private fun <K> MutableMap<K, Long>.increment(key: K, by: Long = 1) {
        put(key, getOrDefault(key, 0) + by)
    }
}