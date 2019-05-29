import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.21"
    application
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":annotation"))
}

application {
    mainClassName = "dev.afanasev.sekret.sample.AppKt"
}

val kotlinPlugin = ":kotlin-plugin"

val compileKotlin by tasks.getting(KotlinCompile::class) {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xplugin=${project(kotlinPlugin).buildDir}/libs/kotlin-plugin.jar")
    }
}

tasks {
    "run" {
        dependsOn(project(kotlinPlugin).getTasksByName("build", false))
    }
}
