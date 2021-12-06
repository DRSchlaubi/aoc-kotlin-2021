package dev.schlaubi.aoc.day_six

import dev.schlaubi.aoc.*


abstract class DaySixTask : Task {
    abstract val days : Int

    override suspend fun run(context: TaskContext) {
        val input = readLines()

        val day = Array(9) { 0L }
        input[0].split(",").forEach { day[it.toInt()]++ }
        repeat(days) {
            val x = day[0]
            (0..7).forEach { day[it] = day[it + 1] }
            day[8] = x
            day[6] += x
        }

        context.output(day.sum())
    }

}
