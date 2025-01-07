// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.geometry

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse

/**
 * Tests for the Direction class and its methods.
 * This class verifies the functionality of Direction methods along with certain properties.
 */
class DirectionTest {

    @Test
    fun `clockWise should rotate the direction 90 degrees clockwise`() {
        val upwardDirection = Direction.Up
        val clockwiseResult = upwardDirection.clockWise()
        assertEquals(Direction.Right, clockwiseResult)
    }

    @Test
    fun `counterClockwise should rotate the direction 90 degrees counterclockwise`() {
        val upwardDirection = Direction.Up
        val counterClockwiseResult = upwardDirection.counterClockwise()
        assertEquals(Direction.Left, counterClockwiseResult)
    }

    @Test
    fun `invert should return the opposite direction`() {
        val upwardDirection = Direction.Up
        val invertedResult = upwardDirection.invert()
        assertEquals(Direction.Down, invertedResult)
    }

    @Test
    fun `isHorizontal should return true for horizontal directions`() {
        val leftDirection = Direction.Left
        val rightDirection = Direction.Right
        assertTrue(leftDirection.isHorizontal)
        assertTrue(rightDirection.isHorizontal)
    }

    @Test
    fun `isHorizontal should return false for non-horizontal directions`() {
        assertFalse(Direction.Up.isHorizontal)
        assertFalse(Direction.Down.isHorizontal)
    }

    @Test
    fun `isVertical should return true for vertical directions`() {
        val upDirection = Direction.Up
        val downDirection = Direction.Down
        assertTrue(upDirection.isVertical)
        assertTrue(downDirection.isVertical)
    }

    @Test
    fun `isVertical should return false for non-vertical directions`() {
        assertFalse(Direction.Left.isVertical)
        assertFalse(Direction.Right.isVertical)
    }

    @Test
    fun `isOpposite should return true for opposite directions`() {
        val leftDirection = Direction.Left
        val rightDirection = Direction.Right
        assertTrue(leftDirection.isOpposite(rightDirection))
        assertTrue(rightDirection.isOpposite(leftDirection))
    }

    @Test
    fun `isOpposite should return false for non-opposite directions`() {
        val leftDirection = Direction.Left
        val upDirection = Direction.Up
        assertFalse(leftDirection.isOpposite(upDirection))
    }

    @Test
    fun `split should return the cardinal directions that compose intercardinal directions`() {
        val downRightDirection = Direction.DownRight
        val splitResult = downRightDirection.split()
        assertEquals(listOf(Direction.Down, Direction.Right), splitResult)
    }

    @Test
    fun `split should return an empty list for cardinal directions`() {
        val rightDirection = Direction.Right
        val splitResult = rightDirection.split()
        assertTrue(splitResult.isEmpty())
    }

    @Test
    fun `asPos should return the corresponding Position`() {
        val direction = Direction(3, -2)
        val position = direction.asPos
        assertEquals(Position(3, -2), position)
    }

    @Test
    fun `asDir should return the same Direction instance`() {
        val direction = Direction(3, -2)
        val result = direction.asDir
        assertEquals(direction, result)
    }

    @Test
    fun `toString should return string representation of direction`() {
        val direction = Direction(2, -3)
        val result = direction.toString()
        assertEquals("(2:-3)", result)
    }
}
