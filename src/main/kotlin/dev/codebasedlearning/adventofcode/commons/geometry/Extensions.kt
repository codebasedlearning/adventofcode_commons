// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.geometry

import kotlin.math.abs

operator fun Position.times(other: Int) = Position(row*other, col*other)
operator fun Position.times(other: Long) = this * other.toInt()
operator fun Int.times(other: Position) = other * this
operator fun Long.times(other: Position) = other * this

operator fun Position.plus(other: Position) = Position(row+other.row, col+other.col)
operator fun Position.unaryMinus() = -1 * this
operator fun Position.minus(other: Position) = this + (-other)

operator fun Position.plus(other: Direction) = Position(row+other.dRow, col+other.dCol)
operator fun Direction.plus(other: Position) = other + this

operator fun Direction.times(other: Int) = Position(dRow*other, dCol*other)
operator fun Direction.times(other: Long) = this * other.toInt()
operator fun Int.times(other: Direction) = other * this
operator fun Long.times(other: Direction) = other * this

operator fun Direction.plus(other: Direction) = Position(dRow+other.dRow, dCol+other.dCol)
operator fun Direction.unaryMinus() = -1 * this

// glide represents more the idea of an infinite window
fun Position.glide(dir: Direction) = sequence {
    var pos = this@glide
    while (true) { yield(pos); pos += dir }
}

// using walk means to get a step, i.e. a position and a direction, or, with a grid, also the grid value
fun Position.walk(seq: Sequence<Direction>) = sequence {
    yieldAll(seq.map { Step(pos=this@walk + it, dir=it) })
}

// often used
fun Position.walkCardinals() = walk(Direction.Cardinals)

fun Position.manhattanDistance(other: Position) = abs(this.row - other.row) + abs(this.col - other.col)
val Position.norm1 get() = abs(row) + abs(col) // |x-0|

fun Direction.manhattanDistance(other: Direction) = this.asPos.manhattanDistance(other.asPos)
val Direction.norm1 get() = this.asPos.norm1
