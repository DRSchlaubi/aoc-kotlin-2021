package dev.schlaubi.aoc.day_two

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task2 : Task {
    override suspend fun run(context: TaskContext) {
        performTask(context, Task2Instruction)
    }
}

sealed interface Task2Instruction : Instruction{
    companion object : InstructionProvider<Task2Instruction>(Task2Instruction::class)

    object Down : Task2Instruction {
        override val name: String = "down"
        override fun Coordinates.apply(value: Int) = copy(aim = aim + value)

    }

    object Up : Task2Instruction {
        override val name: String = "up"
        override fun Coordinates.apply(value: Int) = copy(aim = aim - value)

    }

    object Forward : Task2Instruction {
        override val name: String = "forward"

        override fun Coordinates.apply(value: Int) = copy(horizontal = horizontal + value, depth = depth + aim * value)
    }
}