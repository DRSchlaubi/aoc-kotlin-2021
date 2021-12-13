package dev.schlaubi.aoc.day_thirteen

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val dotAmount = performTask(instructionLimit = 1).sumOf { row -> row.count { dot -> dot } }
        context.output(dotAmount)
    }
}

