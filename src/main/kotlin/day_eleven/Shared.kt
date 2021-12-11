package dev.schlaubi.aoc.day_eleven

import dev.schlaubi.aoc.readLines

fun Any.readOctopus() = readLines().map { it.map(Char::digitToInt).toMutableList() }

fun List<MutableList<Int>>.round(): Pair<Int, List<MutableList<Int>>> {
    val flashed = mutableListOf<Pair<Int, Int>>()

    val byCoordinates = flatMapIndexed { x, ints ->
        ints.mapIndexed { y, i ->
            (x to y) to i
        }
    }

    fun Pair<Int, Int>.flash() {
        if (this in flashed) return
        val (x, y) = this

        // Check if this octopus exists
        if (getOrNull(x)?.getOrNull(y) != null)

        // reset my energy level
            get(x)[y] = 0
        flashed.add(this)

        // Create list of adjacent octopus
        listOf(
                x to (y + 1), // up
                x to (y - 1), // down
                (x - 1) to y, // left
                (x + 1) to y, // right
                (x - 1) to (y + 1), // up left
                (x + 1) to (y + 1), // up right
                (x - 1) to (y - 1), // DOWN left
                (x + 1) to (y - 1) // DOWN right
        ).forEach { coordinates ->
            if (coordinates !in flashed) {
                val (x, y) = coordinates
                val result = getOrNull(x)?.getOrNull(y)?.plus(1) ?: return@forEach
                get(x)[y] = result
                if (result >= 9) { // flash the adjacent octopus if needed
                    coordinates.flash()
                }
            }
        }
    }



    byCoordinates
            .asSequence()
            .filter { (_, energy) -> energy >= 9 }
            .forEach { (coordinates) -> coordinates.flash() }

    val increased = mapIndexed { x, row ->
        row.mapIndexed { y, it ->
            if ((x to y) in flashed) {
                it
            } else {
                it + 1
            }
        }.toMutableList()
    }

    return flashed.size to increased
}
