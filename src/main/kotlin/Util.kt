package dev.schlaubi.aoc

import kotlin.io.path.Path
import kotlin.io.path.readText

/**
 * Tries reading the input file [name] for the current challenge.
 *
 * Please place your input in `resources/day_<day>`
 */
fun Any.readFile(name: String): String {
    val dayPackage = javaClass.packageName.substringAfter("dev.schlaubi.aoc.")
    val resource = ClassLoader.getSystemResource("${dayPackage}/$name")
    requireNotNull(resource) { "Input file ${dayPackage}/$name not found" }
    val path = Path(resource.file)

    return path.readText()
}
