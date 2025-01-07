// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.geometry

/**
 * Represents a direction of movement in a two-dimensional grid.
 *
 * @property dRow The change in row associated with this direction.
 * @property dCol The change in column associated with this direction.
 *
 * This class provides utilities for working with two-dimensional directional vectors,
 * which might be used for navigation, grid-based simulations, or pathfinding algorithms.
 * It implements the `AStep` interface, allowing this direction to also be treated as a step.
 *
 * Directions are defined as `(dRow: dCol)` pairs, where `dRow` indicates the vertical movement
 * and `dCol` indicates the horizontal movement. Certain static properties in the companion
 * object define common directions such as `Up`, `Down`, `Left`, and `Right`, as well as their
 * combinations like `UpLeft` or `DownRight`.
 *
 * Key functional utilities in this class include:
 * - `clockWise`: Rotates the direction clockwise by 90 degrees.
 * - `counterClockwise`: Rotates the direction counterclockwise by 90 degrees.
 * - `invert`: Inverts the direction to point in the opposite direction.
 * - `split`: Splits diagonal directions into their cardinal components.
 *
 * The class also includes boolean properties (`isHorizontal`, `isVertical`) to help determine
 * whether the direction corresponds to purely horizontal or vertical movement, and methods
 * like `isOpposite` to check relationships between directions.
 *
 * The `toString` method provides a formatted string representation of the direction in the form
 * `(dRow:dCol)` for easy debugging and display.
 */
data class Direction(val dRow: Int, val dCol: Int): AStep {
    override val asPos: Position get() = Position(dRow, dCol)
    override val asDir: Direction get() = this

    override fun toString() = "($dRow:$dCol)"

    companion object {
        val Origin = Direction(0, 0)
        val Up = Direction(-1, 0)
        val Down = Direction(+1, 0)
        val Left = Direction(0, -1)
        val Right = Direction(0, +1)
        val UpLeft = Direction(-1, -1)
        val DownLeft = Direction(+1, -1)
        val UpRight = Direction(-1, +1)
        val DownRight = Direction(+1, +1)

        val Cardinals = sequenceOf(Right, Down, Left, Up)
        val InterCardinals = sequenceOf(DownRight, DownLeft, UpLeft, UpRight)
        val AllCardinals = sequenceOf(Right, DownRight, Down, DownLeft, Left, UpLeft, Up, UpRight)
    }

    fun clockWise(): Direction = Direction(dCol, -dRow)
    fun counterClockwise(): Direction = Direction(-dCol, dRow)
    fun invert(): Direction = Direction(-dRow, -dCol)

    val isHorizontal get() = (this == Left) || (this == Right)

    val isVertical get() = (this == Up) || (this == Down)

    fun isOpposite(dir: Direction) = (this == dir.invert())

    fun split() = when (this) {
        UpLeft -> listOf(Up, Left)
        UpRight -> listOf(Up, Right)
        DownRight -> listOf(Down, Right)
        DownLeft -> listOf(Down, Left)
        else -> listOf()
    }
}
