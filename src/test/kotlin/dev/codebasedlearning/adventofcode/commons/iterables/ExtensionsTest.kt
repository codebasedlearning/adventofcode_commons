// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.iterables

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ExtensionsTest {

    /**
     * Unit tests for the Iterable-Extensions functions.
     */

    @Test
    fun `binarySearch finds element in the middle`() {
        val list = listOf(1, 2, 3, 4, 5)
        val target = 3
        val expectedIndex = 2
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch finds element at the beginning`() {
        val list = listOf(1, 2, 3, 4, 5)
        val target = 1
        val expectedIndex = 0
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch finds element at the end`() {
        val list = listOf(1, 2, 3, 4, 5)
        val target = 5
        val expectedIndex = 4
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch returns -1 for an element not in the list`() {
        val list = listOf(1, 2, 3, 4, 5)
        val target = 6
        val expectedIndex = -1
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch returns -1 for an empty list`() {
        val list = emptyList<Int>()
        val target = 1
        val expectedIndex = -1
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch finds element in a list with duplicates`() {
        val list = listOf(1, 2, 2, 2, 3)
        val target = 2
        // val expectedIndex = 2 // Binary search can return any valid index for duplicates
        val result = list.binarySearch(target)
        assert(result in 1..3) // Verifying that the index is valid for duplicates
    }

    @Test
    fun `binarySearch finds element in a single-element list`() {
        val list = listOf(42)
        val target = 42
        val expectedIndex = 0
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch returns -1 for unmatched element in a single-element list`() {
        val list = listOf(42)
        val target = 24
        val expectedIndex = -1
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch works for a list with negative numbers`() {
        val list = listOf(-10, -5, 0, 5, 10)
        val target = -5
        val expectedIndex = 1
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `binarySearch works for a list with positive and negative numbers including zero`() {
        val list = listOf(-3, -1, 0, 1, 3)
        val target = 0
        val expectedIndex = 2
        assertEquals(expectedIndex, list.binarySearch(target))
    }

    @Test
    fun `countWhile counts consecutive matching elements`() {
        val list = listOf(1, 1, 1, 2, 3)
        val startIndex = 0
        val step = 1
        val value = 1
        val expectedCount = 3
        assertEquals(expectedCount, list.countWhile(startIndex, step, value))
    }

    @Test
    fun `countWhile returns 0 for an empty list`() {
        val list = emptyList<Int>()
        val startIndex = 0
        val step = 1
        val value = 1
        val expectedCount = 0
        assertEquals(expectedCount, list.countWhile(startIndex, step, value))
    }

    @Test
    fun `countWhile counts single matching element in the list`() {
        val list = listOf(3)
        val startIndex = 0
        val step = 1
        val value = 3
        val expectedCount = 1
        assertEquals(expectedCount, list.countWhile(startIndex, step, value))
    }

    @Test
    fun `countWhile stops counting when encountering a mismatch`() {
        val list = listOf(4, 4, 5, 4)
        val startIndex = 0
        val step = 1
        val value = 4
        val expectedCount = 2
        assertEquals(expectedCount, list.countWhile(startIndex, step, value))
    }

    @Test
    fun `countWhile counts matching elements with step size`() {
        val list = listOf(1, 2, 1, 3, 1)
        val startIndex = 0
        val step = 2
        val value = 1
        val expectedCount = 3
        assertEquals(expectedCount, list.countWhile(startIndex, step, value))
    }

    @Test
    fun `countWhile works when starting from a middle index`() {
        val list = listOf(3, 3, 3, 4, 3)
        val startIndex = 1
        val step = 1
        val value = 3
        val expectedCount = 2
        assertEquals(expectedCount, list.countWhile(startIndex, step, value))
    }

    @Test
    fun `contentEquals returns true for sequence and iterable with equal content`() {
        val sequence = sequenceOf(1, 2, 3)
        val iterable = listOf(1, 2, 3)
        assert(sequence.contentEquals(iterable))
    }

    @Test
    fun `contentEquals returns false for sequence and iterable with different content`() {
        val sequence = sequenceOf(1, 2, 3)
        val iterable = listOf(1, 2, 4)
        assert(!sequence.contentEquals(iterable))
    }

    @Test
    fun `contentEquals returns true for two sequences with equal content`() {
        val sequence1 = sequenceOf(1, 2, 3)
        val sequence2 = sequenceOf(1, 2, 3)
        assert(sequence1.contentEquals(sequence2))
    }

    @Test
    fun `contentEquals returns false for two sequences with different content`() {
        val sequence1 = sequenceOf(1, 2, 3)
        val sequence2 = sequenceOf(1, 2, 4)
        assert(!sequence1.contentEquals(sequence2))
    }

    @Test
    fun `contentEquals returns false for sequence and empty iterable`() {
        val sequence = sequenceOf(1, 2, 3)
        val iterable = emptyList<Int>()
        assert(!sequence.contentEquals(iterable))
    }

    @Test
    fun `contentEquals returns false for empty sequence and iterable`() {
        val sequence = emptySequence<Int>()
        val iterable = listOf(1, 2, 3)
        assert(!sequence.contentEquals(iterable))
    }

    @Test
    fun `contentEquals returns true for two empty sequences`() {
        val sequence1 = emptySequence<Int>()
        val sequence2 = emptySequence<Int>()
        assert(sequence1.contentEquals(sequence2))
    }
}