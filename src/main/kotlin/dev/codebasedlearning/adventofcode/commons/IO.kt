// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

fun inputFolderNameOf(day: Int) = "./input/day${String.format("%02d", day)}"
fun inputFileOf(day: Int) = File("${inputFolderNameOf(day)}/input.txt")
fun inputFolderOf(day: Int) = File(inputFolderNameOf(day))

fun fetchAoCInputIfNeeded(day: Int, year: Int) {
    if (inputFileOf(day).exists()) return

    val aocSession = "AOC_SESSION_${String.format("%04d", year)}"
    System.getenv(aocSession)?.let { sessionCookie ->
        val input = fetchAdventOfCodeInput(day, year, sessionCookie)
        saveInputData(day, input)
    } ?: throw RuntimeException("'$aocSession' environment variable not set")
}

fun fetchAdventOfCodeInput(day: Int, year: Int, sessionCookie: String): String {
    val client = OkHttpClient()
    val url = "https://adventofcode.com/${String.format("%04d", year)}/day/$day/input"

    val request = Request.Builder()
        .url(url)
        .addHeader("Cookie", "session=$sessionCookie")
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw Exception("Failed to fetch input: ${response.code}")
        return response.body?.string() ?: throw Exception("Empty response body")
    }
}

fun saveInputData(day: Int, data: String) {
    inputFolderOf(day).run {
        if (!exists() && !mkdirs())
            throw IOException("Failed to create directory: ${name}")
    }
    inputFileOf(day).run {
        if (!exists())
            writeText(data)
    }
}
