// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class LinesTests {
    @Test
    fun testLinesOfInputFile() {
        val inputData = linesOf(day = 99, year = 2024)
        assertTrue(inputData == listOf("123", "456", "", "123", "456")) // leading and trailing spaces removed
    }

    @Test
    fun testStory() {
        val examples = listOf<String>()
        val story = object {
            val day = 1
            val year = 2024
            val example = 0
            val lines = when (example) {
                0 -> linesOf(day = day, year = year, fetchAoCInput = true)
                else -> linesOf(input = examples[example-1])
            }
        }.apply {
            lines.print(indent = 2, description = "Day $day, Input:", take = 2)
        }
    }

    @Test
    fun testLinesOfExample() {
        val example = """
            
            
            start
            
            
            789
            
            
        """.trimIndent()
        val inputData = linesOf(input = example)
        assertTrue(inputData == listOf("start", "", "", "789")) // leading and trailing spaces removed
    }
}
