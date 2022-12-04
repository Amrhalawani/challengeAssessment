package com.amrh.data.matches.pojo

import org.junit.Assert.*

import org.junit.Test

class MatchStatusKtTest {

    @Test
    fun passFinishedAsAStringStatus() {

        val actual = isMatchFinished(MatchStatus.FINISHED.status)
        assertEquals(true,actual)
    }

    @Test
    fun passPOSTPONEDAsAStringStatus() {
        val actual = isMatchFinished(MatchStatus.POSTPONED.status)
        assertEquals(false,actual)
    }

    @Test
    fun passRandomStringThatNotIncludedInEnumsWithoutCrash() {
        val actual = isMatchFinished("nameNotIncludedInEnumStatus")
        assertEquals(false,actual)
    }
}