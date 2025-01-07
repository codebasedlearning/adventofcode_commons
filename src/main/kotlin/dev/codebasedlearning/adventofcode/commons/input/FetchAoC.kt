// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.input

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * Ensures that the input file for the specified day and year of Advent of Code exists by fetching it if necessary.
 * If the input file already exists, no additional action is taken.
 * Attempts to fetch the input file from the Advent of Code website using the appropriate session cookie set in the environment variables.
 *
 * @param day The day of the Advent of Code event for which the input file is required. Expects a positive integer.
 * @param year The year of the Advent of Code event for which the input file is required. Expects a positive integer in a four-digit format.
 * @throws RuntimeException if the required session cookie environment variable is not set or invalid.
 */
fun fetchAoCInputIfNeeded(day: Int, year: Int) {
    if (fileOf(day).exists()) return

    // Safari, Dev.tools, Web Inspector, Cookies -> .zshrc
    val aocSessionVar = "AOC_SESSION_${String.format("%04d", year)}"
    System.getenv(aocSessionVar)?.let { sessionCookie ->
        saveInputData(day, fetchAdventOfCodeInput(day, year, sessionCookie))
    } ?: throw RuntimeException("'$aocSessionVar' environment variable not set")
}

/**
 * Fetches the input for a specific day and year of the Advent of Code event using a session cookie.
 * Retrieves the input directly from the Advent of Code website.
 *
 * @param day The day of the Advent of Code event for which input is fetched. Expects a positive integer.
 * @param year The year of the Advent of Code event for which input is fetched. Expects a positive integer in a four-digit format.
 * @param sessionCookie The session cookie used for authentication when making the HTTP request. Expects a non-empty string.
 * @return The input data as a string for the specified day and year.
 * @throws IOException If the HTTP request fails or the response body is empty.
 */
internal fun fetchAdventOfCodeInput(day: Int, year: Int, sessionCookie: String): String {
    val client = OkHttpClient()
    val url = "https://adventofcode.com/${String.format("%04d", year)}/day/$day/input"

    val request = Request.Builder()
        .url(url)
        .addHeader("Cookie", "session=$sessionCookie")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Failed to fetch input: ${response.code}")
        return response.body?.string() ?: throw IOException("Empty response body")
    }
}

/**
 * Saves the input data for a specified day by ensuring the corresponding folder
 * is created and writing the data to the appropriate file. If the folder
 * creation fails, an IOException is thrown.
 *
 * @param day The day number for which the input data is to be saved. Expects a positive integer.
 * @param data The input data as a string to be saved.
 * Throws an exception if the folder cannot be created or if writing to the file fails.
 */
internal fun saveInputData(day: Int, data: String) {
    folderOf(day).run {
        if (!exists() && !mkdirs()) throw IOException("Failed to create directory: $name")
    }
    fileOf(day).run {
        if (!exists()) writeText(data)
    }
}
