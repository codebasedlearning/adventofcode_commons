// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

internal const val reset = "\u001B[0m"
fun colorString(input: String, color: String) = "$color$input$reset"

fun String.inBlack() = colorString(this, "\u001B[30m")
fun String.inRed() = colorString(this, "\u001B[31m")
fun String.inGreen() = colorString(this, "\u001B[32m")
fun String.inYellow() = colorString(this, "\u001B[33m")
fun String.inBlue() = colorString(this, "\u001B[34m")
fun String.inMagenta() = colorString(this, "\u001B[35m")
fun String.inCyan() = colorString(this, "\u001B[36m")
fun String.inWhite() = colorString(this, "\u001B[37m")  // = BrightGray

fun String.inGray() = colorString(this, "\u001B[90m")   // = BrightBlack
fun String.inBrightRed() = colorString(this, "\u001B[91m")
fun String.inBrightGreen() = colorString(this, "\u001B[92m")
fun String.inBrightYellow() = colorString(this, "\u001B[93m")
fun String.inBrightBlue() = colorString(this, "\u001B[94m")
fun String.inBrightMagenta() = colorString(this, "\u001B[95m")
fun String.inBrightCyan() = colorString(this, "\u001B[96m")
fun String.inBrightWhite() = colorString(this, "\u001B[97m")

fun String.inBold() = colorString(this, "\u001B[1m")
fun String.inUnderline() = colorString(this, "\u001B[4m")
fun String.inBlink() = colorString(this, "\u001B[5m")
fun String.inReversed() = colorString(this, "\u001B[7m")
fun String.inHidden() = colorString(this, "\u001B[8m")

fun String.inBlackBackground() = colorString(this, "\u001B[40m")
fun String.inRedBackground() = colorString(this, "\u001B[41m")
fun String.inGreenBackground() = colorString(this, "\u001B[42m")
fun String.inYellowBackground() = colorString(this, "\u001B[43m")
fun String.inBlueBackground() = colorString(this, "\u001B[44m")
fun String.inMagentaBackground() = colorString(this, "\u001B[45m")
fun String.inCyanBackground() = colorString(this, "\u001B[46m")
fun String.inWhiteBackground() = colorString(this, "\u001B[47m")

fun String.inDefaultColor() = colorString(this, "\u001B[39m")
fun String.inDefaultBackground() = colorString(this, "\u001B[49m")
