// (C) 2025 A.Voß, a.voss@fh-aachen.de, info@codebasedlearning.dev

package dev.codebasedlearning.adventofcode.commons.timing

import org.junit.jupiter.api.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TimeResultTests {

    /**
     * Test suite for methods in the `Results` file.
     */

    @Test
    fun `timeResult should wait for 250ms and return the result`() {
        timeResult {
            Thread.sleep(250)
            1L
        }.let { (dt,result) ->
            println("[part 1] result: $result, dt: $dt (context part 1)")
            assertEquals(1L, result)
            assertTrue { dt.inWholeMilliseconds in 200L..300L }
        }
    }

    @Test
    fun `checkResult should wait for 250ms and check the results`() {
        checkResult(23) {
            Thread.sleep(250)
            23L
        }.let { (dt,result,check) ->
            println("[part 2] result: $result $check, dt: $dt (context part 2)")
            assertEquals(23L, result)
            assertContains(check, "OK" )
            assertTrue { dt.inWholeMilliseconds in 200L..300L }
        }

        checkResult(23) {
            Thread.sleep(250)
            24L
        }.let { (dt,result,check) ->
            println("[part 3] result: $result $check, dt: $dt (context part 3)")
            assertEquals(24L, result)
            assertContains(check, "NOK" )
            assertTrue { dt.inWholeMilliseconds in 200L..300L }
        }
    }
}
