package dev.schlaubi.aoc.day_three

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readLines()
        val matrix = input.buildMatrix()


        val gamma = matrix.calculateMeasurement { maxByOrNull(it) }
        val epsilon = matrix.calculateMeasurement { minByOrNull(it) }

        context.output(gamma * epsilon)
    }
}

private fun List<List<Char>>.calculateMeasurement(comparingFunction: ComparingFunction<Char>) = map { row ->
    row.findCommon(comparingFunction) { error("Could not find occurrences") }
}.parseBinary()

private fun List<String>.buildMatrix(): List<List<Char>> {
    val inputLength = first().length // We just assume that the input is the same length for all of them
    return List(inputLength) { index ->
        map { it[index] }
    }
}
