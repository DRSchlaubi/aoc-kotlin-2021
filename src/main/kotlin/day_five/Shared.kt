package dev.schlaubi.aoc.day_five

import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines
import java.awt.geom.Line2D


fun Any.performTask(context: TaskContext, filter: (Line) -> Boolean = { true }) {
    val input = readLines()
            .map {
                val (start, end) = it.split(" -> ")

                Coordinates.fromString(start)..Coordinates.fromString(end)
            }.filter(filter)


    val results = HashMap<Coordinates, Int>()
    input.forEach { line ->
        val (start, end) = line
        for (x in start.x safeRange end.x) {
            for (y in start.y safeRange end.y) {
                val coordinates = Coordinates(x, y)
                if (coordinates in line) {
                    val existing = results[coordinates] ?: 0
                    results[coordinates] = existing + 1
                }
            }
        }
    }

    context.output(results.count { (_, count) -> count >= 2 })
}


data class Line(override val start: Coordinates, override val endInclusive: Coordinates) : ClosedRange<Coordinates> {
    override operator fun contains(value: Coordinates) = Line2D.ptLineDist(start.x.toDouble(),
            start.y.toDouble(),
            endInclusive.x.toDouble(),
            endInclusive.y.toDouble(),
            value.x.toDouble(),
            value.y.toDouble()
    ) == 0.0
}

data class Coordinates(val x: Int, val y: Int) : Comparable<Coordinates> {
    operator fun rangeTo(other: Coordinates) = Line(this, other)
    override fun compareTo(other: Coordinates): Int {
        if (this == other) return 0
        if (this.x == other.x) return this.y.compareTo(other.y)
        return this.x.compareTo(other.x)
    }

    override fun toString(): String = "($x,$y)"

    companion object {
        fun fromString(coordinates: String): Coordinates {
            val (x, y) = coordinates.split(',')

            return Coordinates(x.toInt(), y.toInt())
        }
    }
}

infix fun Int.safeRange(other: Int) = if (this <= other) this..other else this downTo other
