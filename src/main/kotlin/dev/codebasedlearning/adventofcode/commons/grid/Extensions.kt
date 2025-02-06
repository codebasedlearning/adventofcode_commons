// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.grid

import dev.codebasedlearning.adventofcode.commons.geometry.AStep
import dev.codebasedlearning.adventofcode.commons.geometry.Direction
import dev.codebasedlearning.adventofcode.commons.geometry.Position
import dev.codebasedlearning.adventofcode.commons.geometry.Step
import dev.codebasedlearning.adventofcode.commons.input.Lines
import kotlin.collections.component1
import kotlin.collections.component2

val mapKeysToDir = mapOf('^' to Direction.Up, 'v' to Direction.Down, '<' to Direction.Left, '>' to Direction.Right)
val mapDirToKeys = mapKeysToDir.entries.associate { (k, v) -> v to k }

fun Lines.toGrid() = Grid<Char>().apply {
    this.addAll(this@toGrid.map { it.toMutableList() })
}

fun <R> Lines.toGrid(block: (Char) -> R) = Grid<R>().apply {
    this@toGrid.forEach {  add(it.map { c -> block(c) }.toMutableList()) }
}

data class SteppedValue<T>(val pos: Position, val dir: Direction, val value: T): AStep {
    override fun toString() = "(${pos.row}|${pos.col} ${dir.dRow}:${dir.dCol} '$value')"
    override val asPos: Position get() = pos
    override val asDir: Direction get() = dir
}

fun <T> Sequence<Step>.inGrid(grid:Grid<T>) = sequence {
    yieldAll(filter { it.pos in grid }.map { SteppedValue(it.pos,it.dir,grid[it.pos]) })
}

fun <T> Sequence<T>.slice(range: IntRange): Sequence<T> = drop(range.first).take(range.last - range.first + 1)
