// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

typealias Lines = List<String>

fun linesOf(day: Int = -1, year: Int = -1, fetchAoCInput: Boolean = false, input: String? = null): Lines {
    return (input?.lines() ?: run {
        if (fetchAoCInput)
            fetchAoCInputIfNeeded(day, year)
        inputFileOf(day).readLines()
    })
        .dropWhile { it.isBlank() }
        .dropLastWhile { it.isBlank() }
}
