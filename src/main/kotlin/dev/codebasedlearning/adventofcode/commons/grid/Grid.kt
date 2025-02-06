// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.grid

import dev.codebasedlearning.adventofcode.commons.geometry.AStep
import dev.codebasedlearning.adventofcode.commons.geometry.Direction
import dev.codebasedlearning.adventofcode.commons.geometry.Position
import kotlin.sequences.map

data class PositionedValue<T>(val pos: Position, val value: T): AStep {
    override fun toString() = "(${pos.row}|${pos.col} '$value')"
    override val asPos: Position get() = pos
    override val asDir: Direction get() = pos.asDir
}

/**
 * Represents a two-dimensional grid of elements of type T. The grid provides
 * convenient methods to access, modify, and navigate its elements based
 * on their row and column positions.
 *
 * @param T The type of elements stored in the grid.
 *
 * Primary features of this class include:
 * - Flexible construction with an optional initialization block for grid elements.
 * - Support for accessing and modifying elements using their row-column indices
 *   or a `Position` object.
 * - Validation of indices and positions to ensure they lie within grid bounds.
 * - Methods to programmatically modify the grid data like resetting, adding rows,
 *   and more.
 *
 * Constructors:
 * - Default constructor initializes an empty grid.
 * - Grid can be initialized with specific rows and columns and a block to initialize
 *   the values.
 */
class Grid<T>() {
    val data: MutableList<MutableList<T>> = mutableListOf()

    val rows: Int get() = data.size
    val cols: Int get() = data[0].size

    val positions get() = (0 until rows).asSequence().flatMap { row ->
        (0 until cols).asSequence().map { col -> Position(row, col) }
    }

    val entries get() = positions.map { PositionedValue(it, this[it]) }

    constructor(rows: Int, cols: Int, block: (pos: Position) -> T) : this() {
        reset(rows, cols, block)
    }

    fun reset(rows: Int, cols: Int, block: (pos: Position) -> T) {
        data.clear()
        data.addAll(MutableList(rows) { row -> MutableList(cols) { col -> block(Position(row,col)) } })
    }

    fun add(row: MutableList<T>) { data.add(row) }
    fun addAll(rows: Iterable<MutableList<T>>) { data.addAll(rows) }

    operator fun get(row: Int, col: Int): T = data[row][col]
    operator fun get(pos: Position): T = get(pos.row, pos.col)

    operator fun set(row: Int, col: Int, value: T) { data[row][col] = value }
    operator fun set(pos: Position, value: T) { set(pos.row, pos.col, value) }

    fun isValid(row: Int, col: Int) = (row in 0..<rows && col in 0..<cols)
    fun isValid(pos: Position) = isValid(pos.row, pos.col)
    fun isValidRow(row: Int) = (row in 0..<rows)
    fun isValidCol(col: Int) = (col in 0..<cols)

    operator fun contains(index: Position) = isValid(index)
}
