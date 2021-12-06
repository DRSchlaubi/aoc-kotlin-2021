package dev.schlaubi.aoc.day_five

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        performTask(context) { (start, end) ->
            start.x == end.x || start.y == end.y
        }
    }
}
