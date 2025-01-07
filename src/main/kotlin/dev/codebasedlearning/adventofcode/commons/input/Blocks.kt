// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

/**
 * A type alias representing a list of `Lines`, where each `Lines` instance constitutes
 * a block of data. It is often used to structure input data that is logically divided
 * into segments, with each block containing multiple lines of text.
 */
typealias Blocks = List<Lines>

/**
 * Splits a list of lines into blocks based on a custom block separator condition.
 *
 * @param isNewBlock A lambda function that determines whether a line signifies the start of a new block.
 * Defaults to checking if a line is blank.
 * @return A list of blocks, where each block is a list of lines.
 */
fun Lines.toBlocks(isNewBlock: (String) -> Boolean = { it.isBlank() }): Blocks
= this.fold(mutableListOf<Lines>() to mutableListOf<String>()) { (blocks, currentBlock), line ->
    if (isNewBlock(line)) {
        if (currentBlock.isNotEmpty()) blocks.add(currentBlock.toMutableList())
        blocks to mutableListOf()
    } else {
        blocks to currentBlock.apply { add(line) }
    }
}.let { (blocks, currentBlock) ->
    if (currentBlock.isNotEmpty()) blocks.add(currentBlock)
    blocks
}

/**
 * Splits the current block of lines into two parts, representing a header and the remaining lines.
 *
 * @param n The number of lines to include in the header. Defaults to 1.
 * @return A pair where the first element is a `Blocks` containing the first `n` lines (the header),
 *         and the second element is a `Blocks` with the remaining lines after the header.
 */
fun Blocks.splitHeaderBlock(n: Int = 1): Pair<Blocks, Blocks> = Pair(this.take(n), this.drop(n))
