// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.graph

/**
 * Represents an undirected weighted graph structure, where nodes are generic elements of type `T`
 * and edges between nodes can have weights.
 *
 * @param T The type of elements contained in the graph.
 *
 * This graph supports the following operations:
 * - Adding nodes and weighted edges to the graph.
 * - Removing nodes and edges from the graph.
 * - Checking for the presence of nodes.
 * - Retrieving the neighbors and weights of edges for a given node.
 */
class Graph<T>() {
    companion object

    val connections = mutableMapOf<T, MutableMap<T,Long>>()

    val nodes get(): Set<T> = connections.keys
    fun contains(node: T): Boolean = connections.containsKey(node)

    fun neighbors(node: T): Map<T,Long> = connections[node]!!

    fun add(node: T) {
        connections.putIfAbsent(node, mutableMapOf())
    }

    fun remove(node: T) {
        connections.remove(node)?.forEach { neighbor -> connections[neighbor.key]?.remove(node) }
    }

    fun add(from: T, to: T, weight: Long = 1) {
        add(from)
        add(to)
        connections[from]?.put(to, weight)
        connections[to]?.put(from, weight)
    }

    fun remove(from: T, to: T) {
        connections[from]?.remove(to)
        connections[to]?.remove(from)
    }
}
