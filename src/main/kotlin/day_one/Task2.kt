package dev.schlaubi.aoc.day_one

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readFile

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readFile("input.txt").lines()

        val output = input
                .asSequence()
                .map(String::toInt)
                .windowed(3)
                .map { (a, b, c) -> a + b + c }
                .zipWithNext()
                .count { (a, b) -> b > a }

        context.output(output)
    }
}