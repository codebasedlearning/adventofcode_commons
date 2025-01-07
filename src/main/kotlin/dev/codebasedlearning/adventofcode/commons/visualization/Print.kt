// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.visualization

import dev.codebasedlearning.adventofcode.commons.grid.Grid

/**
 * Prints the elements of the list with optional indentation and description.
 *
 * @param indent The number of spaces to indent each element. Default is 0.
 * @param description A description to print before the list elements. Default is an empty string.
 * @param take The number of elements from the list to print. Default is all (max value).
 */
fun <T> Iterable<T>.print(indent: Int = 0, description: String = "", take: Int = Int.MAX_VALUE, skipEndl: Boolean = false) {
    if (description.isNotBlank()) { println(description) }
    val prefix = " ".repeat(indent)
    this.take(take).forEach { row ->
        println("$prefix$row")
    }
    if (!skipEndl) println()
}

fun <T> Grid<T>.print(indent: Int = 0, description: String = "", separator: String =" ") {
    if (description.isNotBlank()) { println(description) }
    val prefix = " ".repeat(indent)
    data.forEach { row -> println(row.joinToString(separator, prefix = prefix)) }
}
