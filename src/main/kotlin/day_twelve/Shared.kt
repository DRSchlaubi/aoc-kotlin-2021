package dev.schlaubi.aoc.day_twelve

import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines
import kotlin.collections.ArrayDeque

fun Any.solveTask(context: TaskContext, task1: Boolean = false) {
    val lines = readLines()
    val caveSystem = mutableMapOf<String, List<String>>()
    lines.map { it.split("-") }.forEach { (a, b) ->
        caveSystem[a] = (caveSystem[a] ?: listOf()) + b
        caveSystem[b] = (caveSystem[b] ?: listOf()) + a
    }
    val validPaths = mutableListOf<Path>()
    val queue = ArrayDeque(listOf(Path("start", listOf("start"), false)))
    while (queue.isNotEmpty()) {
        val path = queue.removeFirst()
        val (curr, visited, twice) = path
        if (curr == "end") {
            validPaths += path
        } else {
            queue += caveSystem[curr]!!.mapNotNull { next ->
                when {
                    next !in visited -> {
                        val newVisited = if (next == next.lowercase()) {
                            visited + next
                        } else {
                            visited
                        }
                        path.copy(currentItem = next, visited = newVisited)
                    }
                    !twice && next != "start" && !task1 -> path.copy(currentItem = next, twice = true)
                    else -> null
                }
            }
        }
    }

    context.output(validPaths.size)
}

data class Path(val currentItem: String, val visited: List<String>, val twice: Boolean)