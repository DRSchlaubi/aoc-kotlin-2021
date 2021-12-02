plugins {
  kotlin("jvm") version "1.6.0"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("reflect"))
  implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-jdk8", "1.6.0-RC")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions.freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
}