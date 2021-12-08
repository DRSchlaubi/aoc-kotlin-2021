package dev.schlaubi.aoc.day_eight

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        context.output(readLines().findEasyReadings().size)
    }
}