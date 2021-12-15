package dev.schlaubi.aoc.day_fifteen

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import java.lang.Integer.max

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        runTask(context) {
            List(size * 5) { x ->
                List(first().size * 5) { y ->
                    val realX = x % size
                    val realY = y % first().size

                    val page = (x / size) + (y / size)
                    val result = get(realX)[realY] + page

                    if (result > 9) {
                        result - 9
                    } else {
                        result
                    }
                }
            }
        }
    }
}
