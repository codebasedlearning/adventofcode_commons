// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class IOTests {
    @Test
    fun testFileOf() {
        // val cwd = System.getProperty("user.dir")
        // println("Current working directory: $cwd")
        val inputData = fileOf(
            day = 98,
            path = "./src/test/resources",
            fileName = "input98.txt"
        ).readLines()
        assertTrue(inputData.size==6)
        assertTrue(inputData == listOf("123", "456", "", "789", "abc", "def"))
    }
}
