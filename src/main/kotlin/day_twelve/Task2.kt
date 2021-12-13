package dev.schlaubi.aoc.day_twelve

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        solveTask(context, task1 = false)
    }
}