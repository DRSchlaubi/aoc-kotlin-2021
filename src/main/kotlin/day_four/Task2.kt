package dev.schlaubi.aoc.day_four

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readLines()
        val numbers = input.first().split(',').map(String::toInt)

        val boards = buildMatrix(input).toMutableList()
        var offsetIndex = 0
        // This solution takes forever, but I don't want to optimize it
        while (boards.isNotEmpty()) {
            val (board, lastNumber, wonAt) = boards.doRound(numbers.subList(offsetIndex, numbers.size))
            offsetIndex = wonAt + 1
            boards.remove(board)
            if (boards.isEmpty()) {
                val unmarkedSum = board.calculateUnmarkedSum()
                context.output(unmarkedSum * lastNumber)
                break
            }
        }
    }
}
