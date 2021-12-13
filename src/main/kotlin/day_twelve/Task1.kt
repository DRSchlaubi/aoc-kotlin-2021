package dev.schlaubi.aoc.day_twelve

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        solveTask(context, true)
    }
}