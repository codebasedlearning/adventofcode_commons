// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

import java.io.File

/**
 * Constructs the folder path for a day's input files based on the given day number.
 *
 * @param day The day number for which the folder path is generated. Expects a positive integer.
 * @return A string representing the folder path in the format "./input/dayXX", where XX is a zero-padded day number.
 */
fun folderNameOf(day: Int) = "./input/day${String.format("%02d", day)}"

/**
 * Constructs a file object pointing to the input file for the specified day.
 *
 * @param day The day number for which the input file is intended. Expects a positive integer.
 * @return A File object representing the path to the input file in the format "./input/dayXX/input.txt", where XX is a zero-padded day number.
 */
fun fileOf(day: Int) = File("${folderNameOf(day)}/input.txt")

/**
 * Constructs a File object representing the folder path for the specified day.
 *
 * @param day The day number for which the folder path is generated. Expects a positive integer.
 * @return A File object representing the folder path in the format "./input/dayXX", where XX is a zero-padded day number.
 */
fun folderOf(day: Int) = File(folderNameOf(day))
