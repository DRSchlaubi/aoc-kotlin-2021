package dev.schlaubi.aoc.day_ten

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val lines = readLines()

        val corruptedLines = lines
                .asSequence()
                .map { it.findSyntaxErrors() }
                .filter { (errors) -> errors == 0 }
                .map { (_, autoCompletions) ->
                    autoCompletions.asReversed().fold(0L) { acc, tag ->
                        (acc * 5) + tag.closeTagScore
                    }
                }
                .filterNot { it == 0L }
                .toList()
                .sorted()
        val half = corruptedLines.lastIndex / 2
        context.output(corruptedLines[half])
    }
}
