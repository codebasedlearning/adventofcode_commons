// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

import dev.codebasedlearning.adventofcode.commons.visualization.print
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class LinesTest {

    /**
     * Test suite for methods in the `Lines` file.
     */

    @Test
    fun `linesOf should return a list of strings from day99_input_txt`() {
        val expected = listOf("123", "456", "", "789", "abc", "def")
        val actual = linesOf(day = 99, year = 2024)
        assertEquals(expected, actual)
    }

    @Test
    fun `linesOf should return a list of strings from input`() {
        val example = """
            
            
            start
            
            
            789
            
            
        """.trimIndent()
        val expected = listOf("start", "", "", "789")
        val actual = linesOf(input = example)

        assertEquals(expected, actual)
    }

    @Test
    fun `story_lines should read actual input data and not be empty`() {
        val story = object {
            val day = 1
            val year = 2024
            val example = 0
            val lines = when (example) {
                0 -> linesOf(day = day, year = year, fetchAoCInput = true)
                else -> linesOf(input = "")
            }
        }.apply {
            lines.print(indent = 2, description = "Day $day, Input:", take = 2)
        }
        Assertions.assertTrue(story.lines.isNotEmpty())
    }
}
