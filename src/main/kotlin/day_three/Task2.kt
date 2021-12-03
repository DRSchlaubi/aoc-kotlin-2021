package dev.schlaubi.aoc.day_three

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readLines()
        val matrix = input.buildMatrix()

        val oxygenRating = matrix.calculateRating('1') { maxByOrNull(it) }.parseBinary()
        val co2ScrubberRating = matrix.calculateRating('0') { minByOrNull(it) }.parseBinary()

        context.output(oxygenRating * co2ScrubberRating)
    }
}

private fun List<List<Char>>.calculateRating(fallback: Char, comparingFunction: ComparingFunction<Char>): List<Char> {
    fold(RatingContext(this)) { (inputs, position), _ ->
        if (inputs.size == 1) {
            return inputs.first()
        }
        val bits = inputs.map { it[position] }

        val mostCommon = bits.findCommonWithCalculatedFallback(comparingFunction, fallback)
        val remainingInputs = inputs.filter { it[position] == mostCommon }

        RatingContext(remainingInputs, position + 1)
    }

    error("Resolving failed")
}

data class RatingContext(val inputs: List<List<Char>>, val position: Int = 0)

private fun List<String>.buildMatrix() = map { it.toList() }
