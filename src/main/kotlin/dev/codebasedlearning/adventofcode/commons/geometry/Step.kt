// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.geometry

/**
 * Represents a step that can either be interpreted as a position in a grid
 * or as a direction of movement.
 *
 * This interface provides two properties:
 * - `asPos`: Represents the step as a grid position.
 * - `asDir`: Represents the step as a direction vector.
 *
 * Classes implementing this interface can be used interchangeably as either
 * positions or directions, making it suitable for use in pathfinding, grid-based
 * systems, or any system where transformations between positions and directions
 * are needed.
 */
interface AStep {
    val asPos: Position
    val asDir: Direction
}

/**
 * Represents a step in a two-dimensional grid system.
 *
 * This class is a concrete implementation of the `AStep` interface, representing a step
 * as both a grid position (`pos`) and a movement direction (`dir`).
 *
 * @property pos The position associated with the step, represented as a `Position` object.
 * @property dir The direction of movement for the step, represented as a `Direction` object.
 *
 * The `asPos` property returns the position (`pos`) of the step, while the `asDir` property
 * returns the direction (`dir`). The step can therefore be interpreted as either a position
 * or a direction, depending on the context.
 *
 * The `toString` method provides a formatted string representation of the step in the form
 * "(row|col dRow:dCol)", where `row` and `col` are derived from the position, and `dRow`
 * and `dCol` represent the direction.
 */
data class Step(val pos: Position, val dir: Direction): AStep {
    override val asPos: Position get() = pos
    override val asDir: Direction get() = dir

    override fun toString() = "(${pos.row}|${pos.col} ${dir.dRow}:${dir.dCol})"
}
