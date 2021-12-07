package dev.schlaubi.aoc.day_seven

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.diff

class Task1 : Task {
    override suspend fun run(context: TaskContext) = performTask(context) { positions, targetPosition ->
        positions.sumOf { targetPosition.diff(it) }
    }
}
