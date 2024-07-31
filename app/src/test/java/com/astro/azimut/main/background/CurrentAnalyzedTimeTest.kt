package com.astro.azimut.main.background

import org.junit.Assert.*

import org.junit.Test
import java.time.ZoneId
import java.time.ZonedDateTime

class CurrentAnalyzedTimeTest {

    private val currentAnalyzedTime: CurrentAnalyzedTime = CurrentAnalyzedTime

    @Test
    fun setDateTime() {
        val zoneId = ZoneId.systemDefault()
        val newDateTime = ZonedDateTime.of(2, 3, 4,
            5, 6, 7, 8, zoneId)
        currentAnalyzedTime.setDateTime(newDateTime)
        val actual = currentAnalyzedTime.getDateTime()
        assertEquals(newDateTime, actual)
    }

    @Test
    fun setDateTime_evenIfAbsurd() {
        val zoneId = ZoneId.systemDefault()
        val newDateTime = ZonedDateTime.of(28492, 3, 4,
            5, 6, 7, 8, zoneId)
        currentAnalyzedTime.setDateTime(newDateTime)
        val actual = currentAnalyzedTime.getDateTime()
        assertEquals(newDateTime, actual)
    }

    @Test
    fun setDateTime_evenIfAbsurd2() {
        val zoneId = ZoneId.systemDefault()
        val newDateTime = ZonedDateTime.of(-28492, 3, 4,
            5, 6, 7, 8, zoneId)
        currentAnalyzedTime.setDateTime(newDateTime)
        val actual = currentAnalyzedTime.getDateTime()
        assertEquals(newDateTime, actual)
    }
}