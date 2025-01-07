// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.geometry

import kotlin.test.Test
import kotlin.test.assertEquals

class PositionTest {

    /**
     * Tests for the Position class
     * The Position class represents a 2D coordinate with row and column values.
     * It provides:
     * - A representation as itself (`asPos`)
     * - A default direction (`asDir`)
     * - A string conversion using `toString()`
     */

    @Test
    fun `test Position properties`() {
        val position = Position(5, 10)
        assertEquals(5, position.row, "Row should be 5")
        assertEquals(10, position.col, "Column should be 10")
    }

    @Test
    fun `test asPos returns self`() {
        val position = Position(3, 7)
        assertEquals(position, position.asPos, "asPos should return the Position instance itself")
    }

    @Test
    fun `test asDir returns default direction`() {
        val position = Position(2, 8)
        val expectedDirection = Direction(0, 0)
        assertEquals(expectedDirection, position.asDir, "asDir should return a default direction (0, 0)")
    }

    @Test
    fun `test toString outputs correct format`() {
        val position = Position(1, 4)
        assertEquals("(1|4)", position.toString(), "toString should output the format '(row|col)'")
    }
}
