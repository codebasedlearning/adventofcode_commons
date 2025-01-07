// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test

class BlocksTest {

    /**
     * Test suite for methods in the `Blocks` file.
     */

    @Test
    fun `toBlocks should return a single block when no separators are present`() {
        val lines: Lines = listOf("Line 1", "Line 2", "Line 3")
        val expected: Blocks = listOf(lines)
        val actual = lines.toBlocks()

        assertEquals(expected, actual)
    }

    @Test
    fun `toBlocks should split lines into multiple blocks on blank lines`() {
        val lines: Lines = listOf(
            "Block 1 - Line 1",
            "Block 1 - Line 2",
            "",
            "Block 2 - Line 1",
            "",
            "Block 3 - Line 1",
            "Block 3 - Line 2"
        )
        val expected: Blocks = listOf(
            lines.slice(0..1),
            lines.slice(3..3),
            lines.slice(5..6)
        )
        val actual = lines.toBlocks()

        assertEquals(expected, actual)
    }

    @Test
    fun `toBlocks should handle no input by returning an empty list`() {
        val lines: Lines = emptyList()
        val expected: Blocks = emptyList()
        val actual = lines.toBlocks()

        assertEquals(expected, actual)
    }

    @Test
    fun `toBlocks should handle a single blank line as an empty block`() {
        val lines: Lines = listOf("")
        val expected: Blocks = emptyList()
        val actual = lines.toBlocks()

        assertEquals(expected, actual)
    }

    @Test
    fun `toBlocks should split lines using custom separator condition`() {
        val lines: Lines = listOf(
            "Header: Data",
            "---",
            "Body: Line 1",
            "Body: Line 2",
            "---",
            "Footer: End"
        )
        val expected: Blocks = listOf(
            lines.slice(0..0),
            lines.slice(2..3),
            lines.slice(5..5)
        )
        val actual = lines.toBlocks { it == "---" }

        assertEquals(expected, actual)
    }

    @Test
    fun `splitHeaderBlock should split into header and remaining blocks correctly with a single header block`() {
        val blocks: Blocks = listOf(
            listOf("Header Line 1", "Header Line 2"),
            listOf("Block 1 - Line 1", "Block 1 - Line 2"),
            listOf("Block 2 - Line 1")
        )
        val expectedHeader: Blocks = listOf(
            blocks[0]
        )
        val expectedRemaining: Blocks = listOf(
            blocks[1],
            blocks[2]
        )
        val (header, remaining) = blocks.splitHeaderBlock(1)

        assertEquals(expectedHeader, header)
        assertEquals(expectedRemaining, remaining)
    }

    @Test
    fun `splitHeaderBlock should split into header and remaining blocks correctly with multiple header blocks`() {
        val blocks: Blocks = listOf(
            listOf("Header 1 - Line 1"),
            listOf("Header 2 - Line 1"),
            listOf("Block 1 - Line 1")
        )
        val expectedHeader: Blocks = listOf(
            blocks[0],
            blocks[1]
        )
        val expectedRemaining: Blocks = listOf(
            blocks[2]
        )
        val (header, remaining) = blocks.splitHeaderBlock(2)

        assertEquals(expectedHeader, header)
        assertEquals(expectedRemaining, remaining)
    }

    @Test
    fun `splitHeaderBlock should handle an empty blocks list by returning two empty lists`() {
        val blocks: Blocks = emptyList()
        val (header, remaining) = blocks.splitHeaderBlock(1)

        assertTrue(header.isEmpty())
        assertTrue(remaining.isEmpty())
    }

    @Test
    fun `splitHeaderBlock should return all blocks in the header if n is larger than the block count`() {
        val blocks: Blocks = listOf(
            listOf("Block 1 - Line 1"),
            listOf("Block 2 - Line 1")
        )
        val (header, remaining) = blocks.splitHeaderBlock(5)

        assertEquals(blocks, header)
        assertTrue(remaining.isEmpty())
    }
}
