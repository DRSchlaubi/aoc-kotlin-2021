package dev.schlaubi.aoc.day_seven

import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readFile

fun interface FuelCalculator {
    fun calculateFuelConsumption(positions: List<Int>, targetPosition: Int): Int
}

@Suppress("NOTHING_TO_INLINE")
inline fun Any.performTask(context: TaskContext, calculator: FuelCalculator) {
    val positions = readFile().split(',').map(String::toInt)
    val maxPosition = positions.maxOf { it }
    val cost = (0 until maxPosition).map { position ->
        position to calculator.calculateFuelConsumption(positions, position)
    }.minOf { (_, fuel) -> fuel }

    context.output(cost)
}

