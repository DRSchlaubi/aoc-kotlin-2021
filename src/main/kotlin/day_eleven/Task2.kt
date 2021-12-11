package dev.schlaubi.aoc.day_eleven

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val octopus = readOctopus()

        var counter = 0
        var foundFirstMegaFlash = false
        var lastRoundResult = octopus

        while (!foundFirstMegaFlash) {
            counter++
            lastRoundResult = lastRoundResult.round().second
            if (lastRoundResult.all { it.all { it == 0 } }) {
                foundFirstMegaFlash = true
            }
        }

        context.output(counter)
    }
}