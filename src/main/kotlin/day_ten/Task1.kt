package dev.schlaubi.aoc.day_ten

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val lines = readLines()

        val errorScores = lines.map { it.findSyntaxErrors().first }

        context.output(errorScores.sum())
    }
}
