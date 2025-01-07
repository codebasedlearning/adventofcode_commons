// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.iterables

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CounterMapTest {

    @Test
    fun `test empty CounterMap has default values`() {
        val counterMap = CounterMap<String>()

        assertTrue(counterMap.isEmpty())
        assertEquals(0L, counterMap["nonexistent_key"])
    }

    @Test
    fun `test CounterMap with iterable constructor`() {
        val counterMap = CounterMap(listOf("a", "b", "a"))

        assertEquals(2L, counterMap["a"])
        assertEquals(1L, counterMap["b"])
        assertEquals(0L, counterMap["c"])
    }

    @Test
    fun `test CounterMap with vararg constructor`() {
        val counterMap = CounterMap("x", "y", "x", "z")

        assertEquals(2L, counterMap["x"])
        assertEquals(1L, counterMap["y"])
        assertEquals(1L, counterMap["z"])
    }

    @Test
    fun `test CounterMap copy constructor`() {
        val originalCounterMap = CounterMap("a", "b", "a")
        val copiedCounterMap = CounterMap(originalCounterMap)

        assertEquals(2L, copiedCounterMap["a"])
        assertEquals(1L, copiedCounterMap["b"])
        assertEquals(0L, copiedCounterMap["c"])
    }

    @Test
    fun `test get method returns default value`() {
        val counterMap = CounterMap<String>()

        assertEquals(0L, counterMap["unknown_key"])
    }

    @Test
    fun `test addAll adds counts from iterable`() {
        val counterMap = CounterMap("a", "b")
        counterMap.addAll(listOf("b", "c", "a", "a"))

        assertEquals(3L, counterMap["a"])
        assertEquals(2L, counterMap["b"])
        assertEquals(1L, counterMap["c"])
    }

    @Test
    fun `test addAll adds counts from another CounterMap`() {
        val counterMap1 = CounterMap("x", "y", "z", "x")
        val counterMap2 = CounterMap("x", "z", "z")

        counterMap1.addAll(counterMap2)

        assertEquals(3L, counterMap1["x"])
        assertEquals(1L, counterMap1["y"])
        assertEquals(3L, counterMap1["z"])
    }

    @Test
    fun `test toString method`() {
        val counterMap = CounterMap("a", "b", "a")
        val result = counterMap.toString()

        assertTrue(result.contains("a=2"))
        assertTrue(result.contains("b=1"))
    }
}
