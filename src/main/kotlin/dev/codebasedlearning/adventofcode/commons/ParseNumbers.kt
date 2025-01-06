// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons


/**
 * Extracts numbers from the receiver string using a specified delimiter. The input string
 * is first trimmed of any leading or trailing whitespace. It is then split into parts based
 * on the provided delimiter. Finally, each part is trimmed individually and converted to
 * a number if possible. The method returns a list of successfully converted numbers.
 *
 * @param delimiter Character used to split the input string into parts.
 * @return A list of numbers extracted from the input string.
 */

inline fun <reified T : Number> String.parseNumbers(delimiter: Char): List<T>
= this.trim().split(delimiter).mapNotNull { part ->
    val trimmed = part.trim()
    when (T::class) {
        Int::class -> trimmed.toIntOrNull() as? T
        Long::class -> trimmed.toLongOrNull() as? T
        Double::class -> trimmed.toDoubleOrNull() as? T
        Float::class -> trimmed.toFloatOrNull() as? T
        else -> throw IllegalArgumentException("Unsupported type: ${T::class}")
    }
}
