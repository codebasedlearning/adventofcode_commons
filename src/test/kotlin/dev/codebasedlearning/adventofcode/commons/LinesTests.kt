// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class LinesTests {
    @Test
    fun testLinesOfApplication() {
        val examples = listOf("""
            123
            456
        """.trimIndent()
        )

        val problem = object {
            val day = 98
            val year = 2024
            val example = 1
            val input = when (example) {
                0 -> linesOf(day = 98, year = year, path = "./src/test/resources")
                else -> linesOf(data = examples[example - 1])
            }
            val dim = if (example==0) 71 else 7
        }.apply {
            //input.print(indent = 2, description = "Day: $day, Dim: $dim, Input:", take = 2)
        }
        assertTrue(problem.input == listOf("123", "456"))
    }

    @Test
    fun testLinesOfInputFile() {
        val inputData = linesOf(day = 98, path = "./src/test/resources")
        assertTrue(inputData == listOf("start", "", "123", "456")) // leading and trailing spaces removed
    }

    @Test
    fun testLinesOfExample() {
        val example = """
            
            
            start
            
            
            789
            
            
        """.trimIndent()
        val inputData = linesOf(data = example)
        assertTrue(inputData == listOf("start", "", "", "789")) // leading and trailing spaces removed
    }
}
