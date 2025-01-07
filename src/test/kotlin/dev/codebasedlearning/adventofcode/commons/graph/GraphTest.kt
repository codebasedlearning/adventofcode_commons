// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.graph

import kotlin.test.*
import org.junit.jupiter.api.Test

class GraphTest {

    @Test
    fun `test adding a node`() {
        val graph = Graph<String>()
        graph.add("A")

        assertTrue(graph.contains("A"))
        assertEquals(setOf("A"), graph.nodes)
    }

    @Test
    fun `test adding an edge`() {
        val graph = Graph<String>()
        graph.add("A", "B", 5)

        assertTrue(graph.contains("A"))
        assertTrue(graph.contains("B"))
        assertEquals(5, graph.neighbors("A")["B"])
        assertEquals(5, graph.neighbors("B")["A"])
    }

    @Test
    fun `test removing a node`() {
        val graph = Graph<String>()
        graph.add("A", "B", 5)
        graph.remove("A")

        assertFalse(graph.contains("A"))
        assertTrue(graph.contains("B"))
        assertTrue(graph.neighbors("B").isEmpty())
    }

    @Test
    fun `test removing an edge`() {
        val graph = Graph<String>()
        graph.add("A", "B", 5)
        graph.remove("A", "B")

        assertTrue(graph.contains("A"))
        assertTrue(graph.contains("B"))
        assertTrue(graph.neighbors("A").isEmpty())
        assertTrue(graph.neighbors("B").isEmpty())
    }

    @Test
    fun `test accessing neighbors`() {
        val graph = Graph<String>()
        graph.add("A", "B", 3)
        graph.add("A", "C", 4)

        val neighbors = graph.neighbors("A")
        assertEquals(2, neighbors.size)
        assertEquals(3, neighbors["B"])
        assertEquals(4, neighbors["C"])
    }

    @Test
    fun `test adding duplicate node`() {
        val graph = Graph<String>()
        graph.add("A")
        graph.add("A")

        assertEquals(1, graph.nodes.size)
    }

    @Test
    fun `test adding duplicate edge`() {
        val graph = Graph<String>()
        graph.add("A", "B", 5)
        graph.add("A", "B", 10)

        val neighborsA = graph.neighbors("A")
        val neighborsB = graph.neighbors("B")
        assertEquals(10, neighborsA["B"])
        assertEquals(10, neighborsB["A"])
    }

    @Test
    fun `test graph is empty initially`() {
        val graph = Graph<String>()

        assertTrue(graph.nodes.isEmpty())
    }
}