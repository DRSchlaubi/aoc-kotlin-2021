package dev.schlaubi.aoc.day_three

typealias ComparingFunction<T> = Map<T, Int>.((Map.Entry<T, Int>) -> Int) -> Map.Entry<T, Int>?

fun List<Char>.parseBinary(): Int = joinToString("", "", "").toInt(2)

fun <T> List<T>.findCommonWithCalculatedFallback(comparingFunction: ComparingFunction<T>, fallback: T): T =
        findCommon(comparingFunction) { fallback }

fun <T> List<T>.findCommon(comparingFunction: ComparingFunction<T>, fallback: () -> T): T {
    val grouping = groupingBy { it }
            .eachCount()
    val max = grouping.comparingFunction { item -> item.value }!!

    // Check if there are more with the same occurances
    if (grouping.count { (_, value) -> value == max.value } > 1) return fallback()
    return max.key
}
