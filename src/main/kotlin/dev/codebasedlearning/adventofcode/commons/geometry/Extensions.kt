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

fun Position.visit(step: Direction) = sequence {
    var pos = this@visit
    while (true) { yield(pos); pos += step }
}

fun Position.visit(seq: Sequence<Direction>) = sequence {
    yieldAll(seq.map { this@visit + it })
}

fun Position.walk(step: Direction) = sequence {
    var pos = this@walk
    while (true) { yield(Step(pos=pos, dir=step)); pos += step }
}

fun Position.walk(seq: Sequence<Direction>) = sequence {
    yieldAll(seq.map { Step(pos=this@walk + it, dir=it) })
}

fun Direction.manhattanDistance(other: Direction) = abs(this.dRow - other.dRow) + abs(this.dCol - other.dCol)
val Direction.norm1 get() = abs(dRow) + abs(dCol) // |x-0|

fun Int.toDirectionSquare() = sequence {
    for (dr in -this@toDirectionSquare..this@toDirectionSquare) {
        for (dc in -this@toDirectionSquare..this@toDirectionSquare) {
            yield(Direction(dr, dc))
        }
    }
}
