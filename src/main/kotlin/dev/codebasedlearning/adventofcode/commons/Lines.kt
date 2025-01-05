// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

typealias Lines = List<String>


/**
 * Reads input lines for a given day from a specified data string or from a default input file.
 *
 * @param day The day number, used to locate the default input file.
 * @param data Optional string containing the data. If provided, this data will be split into lines.
 * If not provided, the method will read lines from the default input file for the given day.
 * Finally, all leading and trailing blank lines are dropped.
 */
fun linesOf(day: Int = -1, year: Int = -1, path: String = ".", data: String? = null): Lines {
    require(day > 0 || data != null)
    return (data?.lines() ?: inputFileOf(
        day = day,
        //path = path,
        //fileName = "input.txt"
    ).readLines())  // use readText for the original textfile
        .dropWhile { it.isBlank() }
        .dropLastWhile { it.isBlank() }
}
