package dev.schlaubi.aoc.day_two

import dev.schlaubi.aoc.Task
import dev.schlaubi.aoc.TaskContext

class Task1 : Task {
    override suspend fun run(context: TaskContext) {
        performTask(context, Task1Instruction)
    }
}

sealed interface Task1Instruction : Instruction {
    companion object : InstructionProvider<Task1Instruction>(Task1Instruction::class)

    object Down : Task1Instruction {
        override val name: String = "down"
        override fun Coordinates.apply(value: Int) = copy(depth = depth + value)

    }

    object Up : Task1Instruction {
        override val name: String = "up"
        override fun Coordinates.apply(value: Int) = copy(depth = depth - value)

    }

    object Forward : Task1Instruction {
        override val name: String = "forward"

        override fun Coordinates.apply(value: Int) = copy(horizontal = horizontal + value)
    }
}
