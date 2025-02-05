package dev.codebasedlearning.adventofcode.commons.grid

import dev.codebasedlearning.adventofcode.commons.geometry.Location

class Space<T> {
        val data: MutableList<MutableList<MutableList<T>>> = mutableListOf() // z,y,x

        val zDim: Int get() = data.size
        val yDim: Int get() = data[0].size
        val xDim: Int get() = data[0][0].size

        val locations get() = (0..<xDim).flatMap { x -> (0..<yDim).flatMap { y -> (0..<zDim).map { z -> Location(x,y,z) } } }

        constructor(xSize: Int, ySize: Int, zSize: Int, block: (loc: Location) -> T) {
            reset(xSize, ySize, zSize, block)
        }

        fun reset(xSize: Int, ySize: Int, zSize: Int, block: (loc: Location) -> T) {
            data.clear()
            data.addAll(MutableList(zSize) { z -> MutableList(ySize) { y -> MutableList(xSize) { x -> block(Location(x,y,z)) } } })
        }

        operator fun get(x: Int, y: Int, z: Int): T = data[z][y][x]
        operator fun get(loc: Location): T = get(loc.x,loc.y,loc.z)

        operator fun set(x: Int, y: Int, z: Int, value: T) { data[z][y][x] = value }
        operator fun set(loc: Location, value: T) { set(loc.x,loc.y,loc.z,value) }

        fun isValid(x: Int, y: Int, z: Int) = (x in 0..<xDim && y in 0..<yDim && z in 0..<zDim)
        fun isValid(loc: Location) = isValid(loc.x,loc.y,loc.z)

        operator fun contains(loc: Location) = isValid(loc)
    }
