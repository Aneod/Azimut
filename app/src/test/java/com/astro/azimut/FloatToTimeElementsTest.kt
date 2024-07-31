package com.astro.azimut

import org.junit.Assert.*

import org.junit.Test

class FloatToTimeElementsTest {

    @Test
    fun getHoursOf_0() {
        val toConvert = 0.00f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 0L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_4() {
        val toConvert = 4.00f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 4L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_4_andLessThan5() {
        val toConvert = 4.30f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 4L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_4_andMoreThan5_always4() {
        val toConvert = 4.99f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 4L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_23() {
        val toConvert = 23.99f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 23L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_24_noIncreased() {
        val toConvert = 24.99f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 24L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_25_noIncreased() {
        val toConvert = 25.99f
        val actual = FloatToTimeElements().getHoursOf(toConvert)
        val expected = 25L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_0() {
        val toConvert = 0.00f
        val actual = FloatToTimeElements().getMinutesOf(toConvert)
        val expected = 0L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_10_is_6() {
        val toConvert = 0.10f
        val actual = FloatToTimeElements().getMinutesOf(toConvert)
        val expected = 6L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_50_is_30() {
        val toConvert = 2.50f
        val actual = FloatToTimeElements().getMinutesOf(toConvert)
        val expected = 30L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_345_is_20and7_but20() {
        val toConvert = 2.345f
        val actual = FloatToTimeElements().getMinutesOf(toConvert)
        val expected = 20L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_99_is_59() {
        val toConvert = 12.99f
        val actual = FloatToTimeElements().getMinutesOf(toConvert)
        val expected = 59L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_100_is_0() {
        val toConvert = 12.0f
        val actual = FloatToTimeElements().getMinutesOf(toConvert)
        val expected = 0L
        assertEquals(expected, actual)
    }
}