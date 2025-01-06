// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertTrue

class ParseNumbersTests {
    @Test
    fun testFunctionality() {
        "1,2,3".parseNumbers<Int>(',').let { assertTrue(it.toList() == listOf(1, 2, 3), "-> $it") }
        "4 5 6".parseNumbers<Long>(' ').let { assertTrue(it.toList() == listOf(4L, 5L, 6L), "-> $it") }
        "1.2, 1.3".parseNumbers<Double>(',').let { assertTrue(it.toList() == listOf(1.2, 1.3), "-> $it") }
        "2.5; 3.1".parseNumbers<Float>(';').let { assertTrue(it.toList() == listOf(2.5f, 3.1f), "-> $it") }
    }

    @Test
    fun testValidity() {
        "1,2a,3,as,b4,5".parseNumbers<Int>(',').let { assertTrue(it.toList() == listOf(1, 3, 5), "-> $it") }
    }

    @Test
    fun testEdgeCases() {
        "abc ; def".parseNumbers<Long>(';').let { assertTrue(it.toList() == listOf<Long>(), "-> $it") }
    }
}
