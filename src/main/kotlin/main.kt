package dev.schlaubi.aoc

import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor

private val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four")

fun main(args: Array<String>) {
    val tasks = args.map {
        val (dayRaw, name) = it.split(":")
        val day = dayRaw.toIntOrNull() ?: error("Invalid day: $dayRaw")
        TaskContainer("dev.schlaubi.aoc.day_${numbers[day - 1]}", name)
    }


    println("Found ${tasks.size} tasks")
    val context = TaskContext()
    tasks.forEach { it.run(context) }
    println("Got outputs: ${context.outputs}")
}

private fun TaskContainer.run(context: TaskContext) {
    val clazz = Class.forName("$pack.$className").kotlin
    val constructor = clazz.primaryConstructor ?: error("Class needs to have a constructor")
    val task = constructor.call() as? Task ?: error("Class needs to implement Task")

    runBlocking {
        println("Starting Task: ${task::class.qualifiedName}")
        task.run(context)
    }
}

private data class TaskContainer(val pack: String, val className: String)

interface Task {
    suspend fun run(context: TaskContext)
}

class TaskContext {
    internal val outputs: MutableList<TaskResult> = mutableListOf()

    data class TaskResult(val from: KClass<*>, val output: Any?) {
        override fun toString(): String = "{${from.simpleName}: $output"
    }

    fun output(output: Any?) {
        val clazz = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).callerClass.kotlin
        outputs.add(TaskResult(clazz, output))
    }
}
