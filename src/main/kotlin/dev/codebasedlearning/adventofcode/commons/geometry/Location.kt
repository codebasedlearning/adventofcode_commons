package dev.codebasedlearning.adventofcode.commons.geometry

data class Location(val x: Int, val y: Int, val z: Int) {
    constructor(xyz: List<Int>) : this(xyz[0], xyz[1], xyz[2])
    constructor(xyz: Triple<Int, Int, Int>) : this(xyz.first, xyz.second, xyz.third)
}
