package dev.schlaubi.aoc.day_eight

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readLines

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        val input = readLines()
        val output = input.map {
            val reading = it.substringBefore('|').trimEnd().split(' ')
            val output = it.substringAfter('|').trim().split(' ')
            reading to output
        }.map { (reading, output) ->
            val easyNumbers = reading.mapNotNull { input ->
                numberMap[input.length]?.let { value -> input to value }
            }.toMap()

            val hardNumbers = reading.filter { input ->
                !easyNumbers.containsKey(input)
            }.map { input ->
                val one = easyNumbers.findNumber(1)
                val four = easyNumbers.findNumber(4)

                val mapping = when (input.length) {
                    // 2, 3, 5
                    5 -> {
                        when (four.countEquality(input)) {
                            2 -> 2 // topRight and midMid are shared
                            3 -> {
                                when (one.countEquality(input)) {
                                    1 -> 5 // 5 does not take topRight segment
                                    2 -> 3 // whilst 3 does
                                    else -> null
                                }
                            }
                            else -> null
                        }
                    }
                    // 6, 9, 0
                    6 -> {
                        when (one.countEquality(input)) {
                            1 -> 6 // 6 does only share bottomRight with 1
                            2 -> {
                                if (four.countEquality(input) == 4) 9 else 0
                            }
                            else -> null
                        }
                    }
                    else -> error("Could not parse number $reading")
                }

                input to (mapping ?: error("Could not determine $input"))
            }

            val map = easyNumbers.toList() + hardNumbers

            output.map {
                map.first { (reading) ->
                    it.length == reading.length && it.all { char -> char in reading }
                }.second
            }
        }

        context.output(output.map {
            it.joinToString("").toInt()
        }.sum())
    }
}

private fun String.countEquality(other: String) = toCharArray().count { it in other }

private operator fun String.minus(other: String) = String((toList() - other.toSet()).toCharArray())

private fun Map<String, Int>.findNumber(number: Int) = entries.first { (_, value) -> value == number }.key
