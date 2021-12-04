package dev.schlaubi.aoc.day_four

import dev.schlaubi.aoc.splitByWhitespaces

fun buildMatrix(input: List<String>): List<Board> {
    return input
            .asSequence()
            .drop(1)
            .filter(String::isNotBlank)
            .chunked(5)
            .map {
                it.map { column ->
                    column
                            .trim()
                            .splitByWhitespaces()
                            .map(String::toInt)
                }
            }
            .map { Board.fromNumbers(it) }
            .toList()
}


fun List<Board>.doRound(numbers: List<Int>): Winner {
    numbers.forEachIndexed { index, drawnNumber ->
        forEach { board ->
            board.rows.forEach {
                it.forEach { number ->
                    if (number.number == drawnNumber) {
                        number.marked = true
                    }
                }
            }

            if (board.hasWon()) {
                return Winner(board, drawnNumber, index)
            }
        }

    }

    error("Could not determine winner: \n${this.joinToString("\nn")}")
}

data class Winner(val board: Board, val lastCall: Int, val gameEndedAtIndex: Int)

data class Board(val rows: List<List<Number>>) {
    data class Number(val number: Int, var marked: Boolean = false)

    fun hasWon(): Boolean {
        if (rows.hasWon()) return true
        return rows.rotate().hasWon()
    }

    fun calculateUnmarkedSum() = rows
            .flatten()
            .filterNot(Number::marked)
            .sumOf(Number::number)

    override fun toString(): String = rows.joinToString("\n")

    companion object {
        fun fromNumbers(numbers: List<List<Int>>) = Board(numbers.map { row -> row.map { Number(it) } })
    }
}

private fun List<List<Board.Number>>.hasWon() = any { row -> row.all { it.marked } }

private fun <T> List<List<T>>.rotate(): List<List<T>> {
    val inputLength = first().size // We just assume that the input is the same length for all of them
    return List(inputLength) { index ->
        map { it[index] }
    }
}
