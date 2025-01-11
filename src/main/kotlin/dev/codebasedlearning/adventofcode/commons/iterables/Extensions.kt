// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.iterables

/**
 * Performs a binary search on a sorted list to find the index of a target element.
 * The list should be sorted in ascending order for the binary search to function correctly.
 *
 * @param target The element to search for in the list.
 * @return The index of the target element if it is present in the list; otherwise, returns -1.
 */
fun <T : Comparable<T>> List<T>.binarySearch(target: T): Int {
    var left = 0
    var right = size - 1

    while (left <= right) {
        val mid = left + (right - left) / 2
        when {
            this[mid] == target -> return mid
            this[mid] < target -> left = mid + 1
            else -> right = mid - 1
        }
    }
    return -1
}

/**
 * Counts the number of consecutive occurrences of a specified value in the list, starting at a given index and moving
 * in specified step increments. The search continues as long as the indices remain within the list bounds and the list
 * elements match the specified value.
 * A predicate: (T) -> Boolean would be more general, but needs a lot more execution time.
 * This function was needed in day 1.
 *
 * @param startIndex The index from which to start counting.
 * @param step The number of positions to move after each match. A positive step moves forward, a negative step moves backward.
 * @param value The value to be counted if it matches the elements of the list.
 * @return The count of consecutive elements matching the specified value.
 */
fun <T> List<T>.countWhile(startIndex: Int, step: Int, value: T): Int {
    var count = 0
    var index = startIndex

    while (index in indices && this[index]==value) {
        count++
        index += step
    }
    return count
}

fun <R> countWhile(predicate: ()-> Boolean, block: () -> R): Long {
    var count = 0L
    while (predicate()) { count++; block() }
    return count
}

fun <T> repeat(input: Iterable<T>): Sequence<T> = sequence {
    while (true) { yieldAll(input) }
}

fun repeat(input: String) = repeat(input.asIterable())

/**
 * Compares the contents of this sequence with the specified iterable for equality based on their elements in order.
 * The sequence and the iterable are considered equal if they have the same number of elements and all elements in
 * corresponding positions are equivalent.
 *
 * @param other The iterable to compare with this sequence.
 * @return `true` if the sequence and the iterable have equal content, `false` otherwise.
 */
fun <T> Sequence<T>.contentEquals(other: Iterable<T>): Boolean = this.contentEquals(other.asSequence())

/**
 * Compares the contents of two sequences for equality based on their elements in order.
 * The sequences are considered equal if they have the same number of elements, and all
 * elements at corresponding positions are equivalent.
 *
 * @param other The sequence to compare with this sequence.
 * @return `true` if both sequences have equal content, `false` otherwise.
 */
fun <T> Sequence<T>.contentEquals(other: Sequence<T>): Boolean {
    val lhs = this.iterator()
    val rhs = other.iterator()

    // or with zip, but we need to check the end, so this is the shortest (imho)
    while (lhs.hasNext() && rhs.hasNext()) {
        if (lhs.next() != rhs.next()) return false
    }
    return !lhs.hasNext() && !rhs.hasNext()
}

/**
 * Transfers all elements from this mutable collection to the specified target mutable collection.
 * The target collection is cleared before adding elements from this collection.
 * After the transfer, this collection is also cleared.
 *
 * @param target The mutable collection to which the elements of this collection are transferred.
 */
fun <T> MutableCollection<T>.shiftTo(target: MutableCollection<T>) {
    target.clear()
    target.addAll(this)
    this.clear()
}
