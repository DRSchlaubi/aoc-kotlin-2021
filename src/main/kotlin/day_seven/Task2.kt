package dev.schlaubi.aoc.day_seven

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task2 : Task {
    override suspend fun run(context: TaskContext) = performTask(context) { positions, targetPosition ->
        positions.sumOf { position ->
            val consume = (position safeRange targetPosition).fold(FUelCalculatingContext()) { context, _ ->
                context.increase()
            }.consumed
            consume
        }
    }
}

private data class FUelCalculatingContext(val consumed: Int = 0, val rate: Int = 1) {
    fun increase(increase: Int = 1) = copy(consumed = consumed + rate, rate = rate + increase)
}

private infix fun Int.safeRange(other: Int) = if (this > other) this downTo (other + 1) else this until other