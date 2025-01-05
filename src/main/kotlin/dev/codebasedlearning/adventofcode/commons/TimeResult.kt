// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import kotlin.time.Duration
import kotlin.time.measureTime

fun <T> timeResult(block: () -> T): Pair<Duration, T> {
    var result: T
    val time = measureTime { result = block() }
    return Pair(time, result)
}

fun <T> checkResult(correct: T, block: () -> T): Triple<Duration, T, String> {
    var result: T
    val time = measureTime { result = block() }
    val check = if (correct == result) "OK".inBrightGreen() else "(NOK - correct: $correct)".inBrightRed()
    return Triple(time, result, check)
}
