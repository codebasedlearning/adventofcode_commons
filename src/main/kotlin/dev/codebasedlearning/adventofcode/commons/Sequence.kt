package dev.codebasedlearning.adventofcode.commons

// (C) 2024 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

fun <T> Sequence<T>.contentEquals(other: Iterable<T>): Boolean = this.contentEquals(other.asSequence())

fun <T> Sequence<T>.contentEquals(other: Sequence<T>): Boolean {
    val lhs = this.iterator()
    val rhs = other.iterator()

    // or with zip, but we need to check the end, so this is the shortest (imho)
    while (lhs.hasNext() && rhs.hasNext()) {
        if (lhs.next() != rhs.next()) return false
    }
    return !lhs.hasNext() && !rhs.hasNext()
}
