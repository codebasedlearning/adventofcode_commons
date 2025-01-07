// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class FetchAoCTest {

    /**
     * Test suite for methods in the `InputFile` and `FetchAoc` files.
     */

    @Test
    fun `print the current working directory`() {
        val cwd = System.getProperty("user.dir")
        println("Current working directory: $cwd")
        Assertions.assertTrue(cwd.isNotBlank())
    }

    @Test
    fun `fileOf should return a list of strings from day99_input_txt`() {
        val expected = listOf("123", "456", "", "789", "abc", "def")
        val actual = fileOf(day = 99).readLines()

        assertEquals(expected, actual)
    }

    @Test
    fun `fetchAoCInput should create a file for the given day`() {
        fetchAoCInputIfNeeded(day = 1, year = 2024)
        Assertions.assertTrue { fileOf(day = 1).exists() }
    }
}
