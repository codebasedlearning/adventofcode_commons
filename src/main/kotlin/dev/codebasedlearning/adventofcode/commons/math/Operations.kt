// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.math

import kotlin.math.abs

/**
 * Calculates the greatest common divisor (GCD) of two integers using the Euclidean algorithm.
 *
 * @param a The first integer.
 * @param b The second integer.
 * @return The greatest common divisor of the two input integers.
 */
fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

/**
 * Calculates the greatest common divisor (GCD) of a collection of integers.
 *
 * @param numbers An iterable collection of integers for which the GCD is to be calculated.
 * @return The greatest common divisor of all integers in the collection.
 */
fun gcd(numbers: Iterable<Long>): Long = numbers.reduce { acc, num -> gcd(acc, num) }

/**
 * Calculates the smallest common multiple (SCM) of two integers.
 *
 * @param a The first integer.
 * @param b The second integer.
 * @return The smallest common multiple of the two input integers.
 */
fun scm(a: Long, b: Long): Long = abs(a * b) / gcd(a, b)

/**
 * Calculates the smallest common multiple (SCM) of a collection of integers.
 *
 * @param numbers An iterable collection of integers for which the smallest common multiple is to be calculated.
 * @return The smallest common multiple of all integers in the collection.
 */
fun scm(numbers: Iterable<Long>) = numbers.reduce { acc, num -> scm(acc, num) }
