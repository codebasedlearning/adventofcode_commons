// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.iterables

/**
 * A specialized map implementation for counting occurrences of elements.
 * `CounterMap` behaves like a regular map but provides additional convenience
 * for counting and aggregating occurrences of keys.
 *
 * @param T The type of keys maintained in this map.
 *
 * The main feature of this map is that it defaults to a count of 0 for any key
 * that does not exist, and allows easy aggregation of counts with methods like `addAll`.
 *
 * Primary features include:
 * - Returns 0 for missing keys instead of `null`.
 * - Supports counting occurrences by adding iterable data or another `CounterMap`.
 * - Provides syntactic sugar for managing counts of keys.
 *
 * Implements the `MutableMap` interface and delegates standard map operations
 * to an internal map instance.
 */
class CounterMap<T>(
    private val delegate: MutableMap<T, Long> = mutableMapOf()
) : MutableMap<T,Long> by delegate {
    constructor(vararg iter:T):this() { addAll(iter.asIterable()) }
    constructor(iter: Iterable<T>):this() { addAll(iter) }
    constructor(map: CounterMap<T>):this() { addAll(map) }

    override operator fun get(key:T):Long =delegate.getOrDefault(key,0L)

    fun addAll(list: Iterable<T>) = apply { list.forEach { this[it] += 1 } }
    fun addAll(map: CounterMap<T>) = apply { map.forEach { this[it.key] += it.value  } }

    override fun toString():String = delegate.toString()
}
