// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.visualization

/*
 * This file defines ANSI escape code-based styling properties for strings to enable formatting
 * with foreground colors, background colors, and text styles in terminal outputs.
 *
 * Foreground Colors (Standard & Bright):
 * - Example Usage: "Text".inRed, "Text".inBrightBlue, "Text".inGreen
 *   - Standard: inBlack, inRed, inGreen, inYellow, inBlue, inMagenta, inCyan, inWhite
 *   - Bright: inBrightBlack, inBrightRed, inBrightGreen, inBrightYellow, inBrightBlue, inBrightMagenta, inBrightCyan, inBrightWhite
 *
 * Background Colors:
 * - Example Usage: "Text".withRedBackground, "Text".withBlueBackground
 *   - Options: withBlackBackground, withRedBackground, withGreenBackground, withYellowBackground,
 *              withBlueBackground, withMagentaBackground, withCyanBackground, withWhiteBackground
 *
 * Text Styles:
 * - Example Usage: "Text".styledBold, "Text".styledUnderline
 *   - Options: styledBold, styledUnderline, styledBlink, styledReversed, styledHidden
 *
 * Reset Styles/Defaults:
 * - Example Usage: "Text".inDefaultColor, "Text".withDefaultBackground
 *   - Options: inDefaultColor, withDefaultBackground
 */

/**
 * Applies a given ANSI escape code to the string for terminal text styling.
 *
 * @param ansiCode The ANSI escape code to apply to the string.
 * @return A new string with the specified ANSI escape code applied.
 */
fun String.stylize(ansiCode: String) = "$ansiCode$this\u001B[0m"

val String.inBlack get() = this.stylize("\u001B[30m")
val String.inRed get() = this.stylize("\u001B[31m")
val String.inGreen get() = this.stylize("\u001B[32m")
val String.inYellow get() = stylize("\u001B[33m")
val String.inBlue get() = stylize("\u001B[34m")
val String.inMagenta get() = stylize("\u001B[35m")
val String.inCyan get() = stylize("\u001B[36m")
val String.inWhite get() = stylize("\u001B[37m")  // = BrightGray

val String.inBrightBlack get() = stylize("\u001B[90m")   // = inGray
val String.inBrightRed get() = stylize("\u001B[91m")
val String.inBrightGreen get() = this.stylize("\u001B[92m")
val String.inBrightYellow get() = stylize("\u001B[93m")
val String.inBrightBlue get() = stylize("\u001B[94m")
val String.inBrightMagenta get() = stylize("\u001B[95m")
val String.inBrightCyan get() = stylize("\u001B[96m")
val String.inBrightWhite get() = stylize("\u001B[97m")

val String.withBlackBackground get() = stylize("\u001B[40m")
val String.withRedBackground get() = stylize("\u001B[41m")
val String.withGreenBackground get() = stylize("\u001B[42m")
val String.withYellowBackground get() = stylize("\u001B[43m")
val String.withBlueBackground get() = stylize("\u001B[44m")
val String.withMagentaBackground get() = stylize("\u001B[45m")
val String.withCyanBackground get() = stylize("\u001B[46m")
val String.withWhiteBackground get() = stylize("\u001B[47m")

val String.inDefaultColor get() = stylize("\u001B[39m")
val String.withDefaultBackground get() = stylize("\u001B[49m")

val String.styledBold get() = stylize("\u001B[1m")
val String.styledUnderline get() = stylize("\u001B[4m")
val String.styledBlink get() = stylize("\u001B[5m")
val String.styledReversed get() = stylize("\u001B[7m")
val String.styledHidden get() = stylize("\u001B[8m")
