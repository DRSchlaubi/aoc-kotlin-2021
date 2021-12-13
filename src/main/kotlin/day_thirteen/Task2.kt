package dev.schlaubi.aoc.day_thirteen

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val matrix = performTask()

        matrix.forEach {
            println(it.joinToString("") { dot -> if (dot) "#" else " " })
        }

        context.output("Yeah uhm, I am not even going to bother parsing this, I hope you can read")
    }
}