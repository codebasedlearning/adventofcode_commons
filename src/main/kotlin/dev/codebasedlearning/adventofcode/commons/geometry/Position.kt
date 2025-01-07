// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.geometry

/**
 * Represents a position in a two-dimensional grid.
 *
 * @property row The row index of the position.
 * @property col The column index of the position.
 *
 * This class implements the `AStep` interface, providing a concrete representation
 * of a grid position. The `asPos` property returns the current position itself,
 * while the `asDir` property returns a neutral direction (0, 0).
 *
 * The `toString` method overrides the default string representation to provide a
 * formatted output in the form "(row|col)".
 */
data class Position(val row: Int, val col: Int): AStep {
    override val asPos: Position get() = this
    override val asDir: Direction get() = Direction(row, col)

    override fun toString() = "($row|$col)"
}
