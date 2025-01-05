// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import java.io.File

fun folderNameOf(day: Int, path: String = ".") = "$path/day${String.format("%02d", day)}/data"


/**
 * Constructs a File object based on the given day and file name.
 *
 * @param day The day number, which is used to use a subdirectory in the format "dayXX".
 * @param fileName The name of the data file within the subdirectory.
 * @return A File object representing the specified file in the "dayXX/data" directory.
 */
fun fileOf(day: Int, path: String = ".", fileName: String = "input.txt") = File("${folderNameOf(day,path)}/$fileName")

fun fetchAoCInputIfNeeded(day: Int, year: Int) {}
