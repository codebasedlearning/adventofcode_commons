// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException
import java.util.Properties

private const val inputFileName = "input.txt"
private const val configFileName = "config.properties"
private const val sessionName = "session"

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
fun folderOf(day: Int) = File(folderNameOf(day))

fun inputFileOf(day: Int) = fileOf(day, inputFileName)

fun fetchAoCInputIfNeededLocal(day: Int) {
    if (inputFileOf(day).exists()) return

    // val sessionCookie = getSessionCookie(loadConfigProperties())
    val sessionCookie: String = System.getenv("AOC_SESSION_2024") // ?: "-"

    val input = fetchAdventOfCodeInput(day, sessionCookie)
    saveInputData(day, input)
}

fun fetchAdventOfCodeInput(day: Int, sessionCookie: String): String {
    val client = OkHttpClient()
    val url = "https://adventofcode.com/2024/day/$day/input"

    val request = Request.Builder()
        .url(url)
        .addHeader("Cookie", "session=$sessionCookie")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw Exception("Failed to fetch input: ${response.code}")
        return response.body?.string() ?: throw Exception("Empty response body")
    }
}

fun loadConfigProperties() = Properties().apply {
    File(configFileName).apply {
        if (!exists()) throw RuntimeException("$configFileName not found")
        inputStream().use { load(it) }
    }
}

fun getSessionCookie(properties: Properties)
        = properties.getProperty(sessionName) ?: throw RuntimeException("$sessionName cookie not found")

fun saveInputData(day: Int, data: String) {
    val folder = folderOf(day)
    if (!folder.exists()) {
        if (!folder.mkdirs()) {
            throw IOException("Failed to create directory: ${folder.name}")
        }
    }

    val file = fileOf(day, inputFileName)
    if (!file.exists()) {
        file.writeText(data)
    }
}
