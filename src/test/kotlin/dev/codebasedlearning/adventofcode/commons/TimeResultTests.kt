// (C) 2025 A.Voß, a.voss@fh-aachen.de, kotlin@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons

import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class TimeResultTests {
    @Test
    fun testTimeResult() {
        timeResult {
            sleep(250)
            1L
        }.let { (dt,result) -> println("[part 1] result: $result, dt: $dt (context part 1)") }
    }

    @Test
    fun testCheckResult() {
        checkResult(23) {
            sleep(250)
            23L
        }.let { (dt,result,check) -> println("[part 2] result: $result $check, dt: $dt (context part 2)") }

        checkResult(23) {
            sleep(250)
            24L
        }.let { (dt,result,check) -> println("[part 3] result: $result $check, dt: $dt (context part 3)") }
    }
}
