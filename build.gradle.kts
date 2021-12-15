plugins {
  kotlin("jvm") version "1.6.0"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("reflect"))
  implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", "1.6.0-RC")
  implementation("com.github.xaguzman", "pathfinding", "0.2.6")


}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
}