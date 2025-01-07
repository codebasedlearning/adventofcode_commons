// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

/**
 * A type alias representing a list of strings, often used to work with multiline input data
 * such as files or text buffers. Each string in the list represents a single line.
 */
typealias Lines = List<String>

/**
 * Reads the input for a specific day and year of the Advent of Code event, processes it, and returns the inner non-blank lines.
 * The input can be provided explicitly, fetched from a remote source, or read from a local file.
 *
 * @param day The day of the Advent of Code event for which input is retrieved. Defaults to -1, indicating no specific day.
 * @param year The year of the Advent of Code event for which input is retrieved. Defaults to -1, indicating no specific year.
 * @param fetchAoCInput A flag indicating whether to fetch the input from a remote source if it is not provided locally. Defaults to false.
 * @param input An optional string representing the input data. If provided, this input is used directly.
 * @return A list of non-blank lines from the input data as a `Lines`.
 */
fun linesOf(day: Int = -1, year: Int = -1, fetchAoCInput: Boolean = false, input: String? = null): Lines {
    return (input?.lines() ?: run {
        if (fetchAoCInput) fetchAoCInputIfNeeded(day, year)
        fileOf(day).readLines()
    })
        .dropWhile { it.isBlank() }
        .dropLastWhile { it.isBlank() }
}
