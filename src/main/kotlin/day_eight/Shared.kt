package dev.schlaubi.aoc.day_eight

val numberMap = mapOf(
        2 to 1, // 1 uses two segments
        4 to 4, // 4 uses 4 segments
        3 to 7, // 7 uses 3 segments
        7 to 8 // 8 uses all 7 segments
)

fun List<String>.findEasyReadings(): List<Pair<String, Int>> {
    return flatMap {
        it.substringAfter('|').trim().split(' ')
    }.mapNotNull {input ->
        numberMap[input.length]?.let { input to it }
    }
}