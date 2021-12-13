package dev.schlaubi.aoc.day_thirteen

import dev.schlaubi.aoc.readLines

fun Any.performTask(instructionLimit: Int? = null): List<List<Boolean>> {
    val text = readLines()
    val dots = text.takeWhile { it.isNotBlank() }.map {
        val (x, y) = it.split(',')
        Dot(x.toInt(), y.toInt())
    }
    val instructions = text.subList(dots.size + 1, text.size).map {
        val (type, value) = it.substringAfter("fold along ").split('=')

        Instruction(Axis.valueOf(type), value.toInt())
    }

    val matrix = List(dots.maxOf { (_, y) -> y } + 1) { y ->
        List(dots.maxOf { (x) -> x } + 1) { x ->
            Dot(x, y) in dots
        }
    }.fold(instructions.take(instructionLimit ?: instructions.size))


    return matrix
}

private fun List<List<Boolean>>.fold(instructions: Iterable<Instruction>): List<List<Boolean>> =
        instructions.fold(this) { acc, (axis, value) ->
            acc.fold(axis, value)
        }

private fun List<List<Boolean>>.fold(axis: Axis, point: Int): List<List<Boolean>> {
    val maxX = if (axis == Axis.y) point else size
    return List(maxX) { x ->
        val maxY = if (axis == Axis.x) point else get(x).size
        List(maxY) { y ->
            get(x)[y] || run {
                val possibleFoldedDot = if (axis == Axis.x) {
                    getOrNull(x)?.getOrNull(get(x).size - 1 - y)
                } else {
                    getOrNull(size - 1 - x)?.getOrNull(y)
                }
                possibleFoldedDot ?: false
            }
        }
    }
}

data class Dot(val x: Int, val y: Int)

data class Instruction(val type: Axis, val value: Int)

enum class Axis { x, y }