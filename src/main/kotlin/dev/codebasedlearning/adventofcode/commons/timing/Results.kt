// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.timing

import dev.codebasedlearning.adventofcode.commons.visualization.inBrightGreen
import dev.codebasedlearning.adventofcode.commons.visualization.inBrightRed
import kotlin.time.Duration
import kotlin.time.measureTime

/**
 * Measures the execution time of a given block and associates it with the block's result.
 *
 * @param block The block of code to be executed and measured.
 * @return A pair consisting of the duration it took to execute the block and the result of the block.
 */
fun <T> timeResult(block: () -> T): Pair<Duration, T> {
    var result: T
    val time = measureTime { result = block() }
    return Pair(time, result)
}

/**
 * Measures the execution time of the provided block, evaluates the result
 * against the expected value, and returns a Triple containing the duration,
 * the result, and the outcome of the comparison.
 *
 * @param correct The expected correct value of the result.
 * @param block The block of code to be executed and measured.
 * @return A Triple containing the duration of execution, the result of the block,
 *         and a string indicating whether the result matches the expected value ("OK" or "NOK").
 */
fun <T> checkResult(correct: T, block: () -> T): Triple<Duration, T, String> {
    var result: T
    val time = measureTime { result = block() }
    val check = if (correct == result) "OK".inBrightGreen else "(NOK - correct: $correct)".inBrightRed
    return Triple(time, result, check)
}
