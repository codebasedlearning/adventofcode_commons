// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

import java.io.FileInputStream
import java.util.Properties
import kotlin.apply


plugins {
    kotlin("jvm") version "2.1.0" // id("org.jetbrains.kotlin.jvm")
    id("maven-publish")
    id("signing")
}

group = "dev.codebasedlearning.adventofcode.commons"
version = "0.1.8" // ./gradlew publish

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Implementation-Title"] = "CodeBasedLearning AdventOfCode Commons"
        attributes["Implementation-Version"] = version
        //attributes["Main-Class"] = "com.codebasedlearning.aoc.MainKt" // Adjust this to your main class, if needed
    }
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

// Load custom properties file
//val localProperties = Properties().apply {
//    val file = file("local.properties") // Replace with your file name if different
//    if (file.exists()) {
//        load(FileInputStream(file))
//    }
//}
val githubActor: String? = System.getenv("GITHUB_CBL_ACTOR") // ?: "-"
val githubToken: String? = System.getenv("GITHUB_CBL_TOKEN") // ?: "-"
//println("github_actor: $github_actor !!! github_token: $github_token")
publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/codebasedlearning/adventofcode_commons")
            credentials {
                username = githubActor
                password = githubToken
            }
        }
    }

    publications {
        create<MavenPublication>("github") {
            from(components["kotlin"]) // or "kotlin" or "android"
            pom {
                name.set("adventofcode_commons")
                description.set("A library for solving Advent of Code problems.")
                url.set("https://github.com/codebasedlearning/adventofcode_commons")
                scm {
                    connection.set("scm:git:git://github.com/codebasedlearning/adventofcode_commons.git")
                    developerConnection.set("scm:git:ssh://github.com/codebasedlearning/adventofcode_commons.git")
                    url.set("https://github.com/codebasedlearning/adventofcode_commons")
                }
            }
        }
    }
}

