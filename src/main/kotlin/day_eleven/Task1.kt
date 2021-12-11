package dev.schlaubi.aoc.day_eleven

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        val octopus = readOctopus()

        val (flashes) = octopus.rounds(100)
        context.output(flashes)
    }

    private fun List<MutableList<Int>>.rounds(rounds: Int) = (0 until rounds)
            .fold(0 to this) { (totalFlashes, context), _ ->
                val (flashes, octopus) = context.round()
                (totalFlashes + flashes) to octopus
            }
}