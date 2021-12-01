package dev.schlaubi.aoc.day_one

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readFile

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readFile("input.txt").lines()

        val output = input
                .map(String::toInt)
                .zipWithNext()
                .count { (a, b) -> b > a }
        context.output(output)
    }
}
