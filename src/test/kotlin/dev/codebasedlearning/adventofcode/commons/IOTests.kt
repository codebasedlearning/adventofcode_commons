// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class IOTests {
    @Test
    fun testCwd() {
        val cwd = System.getProperty("user.dir")
        println("Current working directory: $cwd")
        assertTrue(cwd.isNotBlank())
    }

    @Test
    fun testInputFileOf() {
        val inputData = inputFileOf(day = 99).readLines()
        assertTrue(inputData.size==6)
        assertTrue(inputData == listOf("123", "456", "", "789", "abc", "def"))
    }

    @Test
    fun testFetchAoCInput() {
        fetchAoCInputIfNeeded(day=1,year=2024)
    }
}
