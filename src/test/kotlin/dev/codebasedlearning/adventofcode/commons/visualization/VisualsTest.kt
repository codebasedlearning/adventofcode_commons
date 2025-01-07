// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.visualization

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class TimeResultTests {

    /**
     * Test suite for methods in the `visualization` folder - check visually.
     */

    @Test
    fun `colors, backgrounds and styles should work`() {
        println(" 1| '${"in Red".inRed}'")
        println(" 2| '${"in BrightBlue with YellowBackground".inBrightBlue.withYellowBackground}'")
        println(" 3| '${"in Green and styled Bold".inGreen.styledBold}'")
        assertTrue { true }
    }

    @Test
    fun `print should work`() {
        println(" 1| before List")
        val list = listOf("a", "b", "c")
        list.print(indent = 2, description = " 2| List of Strings (only 2)", skipEndl = true, take = 2)
        println(" 3| after List")
        assertTrue { true }
    }
}
