package dev.codebasedlearning.adventofcode.commons.geometry

import dev.codebasedlearning.adventofcode.commons.grid.Grid
import dev.codebasedlearning.adventofcode.commons.grid.inGrid
import org.junit.jupiter.api.Test

class ExtensionsKtTest {
    @Test
    fun `move into`() {

        // temp

        val l1a = Position(2, 1).glide(Direction.Right).take(5).toList()
        println(l1a)
        val l1aa = Position(2, 1).glide(Direction.Right).takeWhile { it.col<4 }.toList()
        println(l1aa)

        val l2 = Position(2, 1).walk(Direction.Cardinals).toList()
        println(l2)
        val l2b = Position(2, 1).walkCardinals().withIndex().map { it }.toList()
        println(l2b)

        val grid = Grid(2,3) { 'x' }
        val l3a = Position(0, 1).walk(Direction.Cardinals).inGrid(grid).toList()
        println(l3a)
        val l3b = Position(0, 1).walkCardinals().inGrid(grid).toList()
        println(l3b)
        //val l3c = Position(1, 1).glide(Direction.Right).take(1).inGrid(grid).toList()
        //println(l3c)

    }

}