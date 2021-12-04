package dev.schlaubi.aoc.day_four

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readLines()
        val numbers = input.first().split(',').map(String::toInt)

        val boards = buildMatrix(input)
        val (board, lastNumber) = boards.doRound(numbers)
        val unmarkedSum = board.calculateUnmarkedSum()
        context.output(unmarkedSum * lastNumber)
    }
}
