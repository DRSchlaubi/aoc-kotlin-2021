package dev.schlaubi.aoc

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText
import kotlin.math.abs

private val onePlusWhitespacesRegex = "\\s+".toRegex()

/**
 * Tries reading the input file [name] for the current challenge.
 *
 * Please place your input in `resources/day_<day>`
 */
fun Any.readFile(name: String = "input.txt"): String = determinePath(name).readText()

/**
 * Tries reading the input file [name] for the current challenge as lines.
 *
 * Please place your input in `resources/day_<day>`
 */
fun Any.readLines(name: String = "input.txt"): List<String> = determinePath(name).readLines()

private fun Any.determinePath(name: String): Path {
    val dayPackage = javaClass.packageName.substringAfter("dev.schlaubi.aoc.")
    val resource = ClassLoader.getSystemResource("${dayPackage}/$name")
    requireNotNull(resource) { "Input file ${dayPackage}/$name not found" }
    return Path(resource.file)
}

fun String.splitByWhitespaces() = split(onePlusWhitespacesRegex)

fun <T> T.repeat(times: Int) = generateSequence { this }.take(times).toList()

fun Int.diff(b: Int) = abs(this - b)
