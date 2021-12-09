package dev.schlaubi.aoc.day_nine

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val response = parseHeights()
                .findLowPoints()
                .map { (_, _, number) -> number }
                .map { 1 + it } // RIsk level is 1 + height
                .sum()

        context.output(response)
    }
}
