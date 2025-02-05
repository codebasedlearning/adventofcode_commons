// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.grid

import dev.codebasedlearning.adventofcode.commons.geometry.AStep
import dev.codebasedlearning.adventofcode.commons.geometry.Direction
import dev.codebasedlearning.adventofcode.commons.geometry.Position
import dev.codebasedlearning.adventofcode.commons.geometry.Step
import dev.codebasedlearning.adventofcode.commons.input.Lines
import kotlin.collections.component1
import kotlin.collections.component2

//val mapDirToKeys = mapOf('^' to Direction.Up, 'v' to Direction.Down, '<' to Direction.Left, '>' to Direction.Right)
//val mapKeysToDir = mapDirToKeys.entries.associate { (k, v) -> v to k }
val mapKeysToDir = mapOf('^' to Direction.Up, 'v' to Direction.Down, '<' to Direction.Left, '>' to Direction.Right)
val mapDirToKeys = mapKeysToDir.entries.associate { (k, v) -> v to k }

fun Lines.toGrid() = Grid<Char>().apply {
    this.addAll(this@toGrid.map { it.toMutableList() })
}

fun <R> Lines.toGrid(block: (Char) -> R) = Grid<R>().apply {
    this@toGrid.forEach {  add(it.map { c -> block(c) }.toMutableList()) }
}

//fun <T> Grid<T>.copy():Grid<T> {
//    val f = Grid<T>()
//    for (l in data) {
//        f.data.add(l.toMutableList())
//    }
//    return f
//}

//inline fun <T,R> Grid<T>.temporarilyReplace(pos: Position, value: T, block: Grid<T>.() -> R): R {
//    val original = this[pos]
//    this[pos] = value
//    return try {
//        block()
//    } finally {
//        this[pos] = original
//    }
//}

//fun <T> Grid<T>.forEachWithPosition(block: (Position, T) -> Unit) {
//    positions.forEach { pos -> block(pos, this[pos]) }
//}

//data class GridValue<T>(val pos: Position, val dir:Direction, val value: T): AStep {
//    override fun toString() = "(${pos.row}|${pos.col} ${dir.dRow}:${dir.dCol} '$value')"
//    override val asPos: Position get() = pos
//    override val asDir: Direction get() = dir
//}

//fun <T,S:AStep> Sequence<S>.takeWhileInGrid(grid: Grid<T>) = this.takeWhile { it.asPos in grid }
//
//fun <T,S:AStep> Sequence<S>.withGrid(grid: Grid<T>) = this.takeWhile { it.asPos in grid }.map { GridValue(it.asPos, it.asDir, grid[it.asPos]) }
//
//fun <T,S:AStep> Sequence<S>.toGrid(grid: Grid<T>) = this.takeWhile { it.asPos in grid }.map { grid[it.asPos] }

data class SteppedValue<T>(val step: Step, val value: T): AStep {
    override fun toString() = "(${step.pos.row}|${step.pos.col} ${step.dir.dRow}:${step.dir.dCol} '$value')"
    override val asPos: Position get() = step.pos
    override val asDir: Direction get() = step.dir
}

fun <T> Sequence<Step>.inGrid(grid:Grid<T>) = sequence {
    yieldAll(filter { it.pos in grid }.map { SteppedValue(it,grid[it.pos]) })
}

fun <T> Sequence<T>.slice(range: IntRange): Sequence<T> = drop(range.first).take(range.last - range.first + 1)
