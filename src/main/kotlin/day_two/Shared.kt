package dev.schlaubi.aoc.day_two

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext
import dev.schlaubi.aoc.readFile
import kotlin.reflect.KClass

data class Coordinates(val horizontal: Int = 0, val depth: Int = 0, val aim: Int = 0)

interface Instruction {
    val name: String
    fun Coordinates.apply(value: Int): Coordinates
}

abstract class InstructionProvider<T : Instruction>(private val instructionClass: KClass<T>) {

    private val values: List<T> by lazy {
        instructionClass.sealedSubclasses.map {
            it.objectInstance ?: error("Missing object instance for ${it.simpleName}")
        }
    }

    fun forName(name: String) = values.firstOrNull { it.name == name }
            ?: error("Could not find instruction for: $name")
}

fun <T : Instruction> Task.performTask(context: TaskContext,
                                       instructionProvider: InstructionProvider<T>,
                                       inputCleaner: (Coordinates) -> Any? = { (horizontal, depth) -> horizontal * depth }
) {
    val instructions = readFile("input.txt").lines()

    val coordinates = instructions.fold(Coordinates(0, 0)) { coordinates, inputString ->
        val (instructionName, input) = inputString.split(' ')
        val instruction = instructionProvider.forName(instructionName)

        with(instruction) {
            coordinates.apply(input.toInt())
        }
    }

    context.output(inputCleaner(coordinates))
}