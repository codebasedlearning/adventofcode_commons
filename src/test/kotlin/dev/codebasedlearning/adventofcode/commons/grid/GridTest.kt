// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.grid

import dev.codebasedlearning.adventofcode.commons.geometry.Position
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class GridTest {

    /**
     * Test suite for class `grid` in the `Grid` file.
     */

    @Test
    fun `test grid initialization with dimensions`() {
        val grid = Grid(4, 3) { Position(it.row, it.col) }
        assertEquals(4, grid.rows)
        assertEquals(3, grid.cols)
        assertEquals(Position(2, 1), grid[2, 1])
    }

    @Test
    fun `test reset function`() {
        val grid = Grid(3, 3) { 0 }
        grid.reset(3, 2) { 1 }
        assertEquals(3, grid.rows)
        assertEquals(2, grid.cols)
        assertEquals(1, grid[0, 0])
        assertEquals(1, grid[1, 1])
    }

    @Test
    fun `test add single row`() {
        val grid = Grid(0, 0) { 0 }
        grid.add(mutableListOf(1, 2, 3))
        assertEquals(1, grid.rows)
        assertEquals(3, grid.cols)
        assertEquals(2, grid[0, 1])
    }

    @Test
    fun `test add multiple rows`() {
        val grid = Grid(0, 0) { 0 }
        grid.addAll(
            listOf(
                mutableListOf(1, 2, 3),
                mutableListOf(4, 5, 6)
            )
        )
        assertEquals(2, grid.rows)
        assertEquals(3, grid.cols)
        assertEquals(4, grid[1, 0])
        assertEquals(5, grid[1, 1])
    }

    @Test
    fun `test element access by Position`() {
        val grid = Grid(2, 2) { Position(it.row, it.col) }
        assertEquals(Position(0, 1), grid[Position(0, 1)])
        assertEquals(Position(1, 0), grid[Position(1, 0)])
    }

    @Test
    fun `test element set by Position`() {
        val grid = Grid(2, 2) { 0 }
        grid[Position(1, 1)] = 42
        assertEquals(42, grid[1, 1])
    }

    @Test
    fun `test isValid method`() {
        val grid = Grid(3, 3) { 0 }
        assertTrue(grid.isValid(1, 1))
        assertFalse(grid.isValid(3, 3))
        assertTrue(grid.isValid(Position(0, 0)))
        assertFalse(grid.isValid(Position(-1, -1)))
    }

    @Test
    fun `test isValidRow method`() {
        val grid = Grid(3, 3) { 0 }
        assertTrue(grid.isValidRow(2))
        assertFalse(grid.isValidRow(3))
    }

    @Test
    fun `test isValidCol method`() {
        val grid = Grid(3, 3) { 0 }
        assertTrue(grid.isValidCol(1))
        assertFalse(grid.isValidCol(5))
    }

    @Test
    fun `test contains operator`() {
        val grid = Grid(3, 3) { 0 }
        assertTrue(Position(0, 0) in grid)
        assertFalse(Position(3, 3) in grid)
    }
}