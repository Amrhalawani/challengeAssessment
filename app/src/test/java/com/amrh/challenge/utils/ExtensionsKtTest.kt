package com.amrh.challenge.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun getDate() {
        val actual = getDateFormattedYYYYMMDD("2023-05-06T14:00:00Z")

        val expected = "2023-05-06"
        assertEquals(expected, actual)

    }

    @Test
    fun getTime() {

        val actual = getTimeFormattedHHMM("2023-05-06T14:00:00Z")

        val expected = "14:00"
        assertEquals(expected, actual)
    }
}