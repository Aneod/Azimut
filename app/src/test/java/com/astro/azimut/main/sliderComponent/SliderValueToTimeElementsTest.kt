package com.astro.azimut.main.sliderComponent

import org.junit.Assert.*

import org.junit.Test
import java.time.ZonedDateTime

class SliderValueToTimeElementsTest {

    @Test
    fun getHoursOf_0() {
        val toConvert = 0.00f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 0L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_4() {
        val toConvert = 4.00f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 4L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_4_andLessThan5() {
        val toConvert = 4.30f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 4L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_4_andMoreThan5_always4() {
        val toConvert = 4.99f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 4L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_23() {
        val toConvert = 23.99f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 23L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_24_noIncreased() {
        val toConvert = 24.99f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 24L
        assertEquals(expected, actual)
    }

    @Test
    fun getHoursOf_25_noIncreased() {
        val toConvert = 25.99f
        val actual = SliderValueToTimeElements().getHoursOf(toConvert)
        val expected = 25L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_0() {
        val toConvert = 0.00f
        val actual = SliderValueToTimeElements().getMinutesOf(toConvert)
        val expected = 0L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_10_is_6() {
        val toConvert = 0.10f
        val actual = SliderValueToTimeElements().getMinutesOf(toConvert)
        val expected = 6L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_50_is_30() {
        val toConvert = 2.50f
        val actual = SliderValueToTimeElements().getMinutesOf(toConvert)
        val expected = 30L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_345_is_20and7_but20() {
        val toConvert = 2.345f
        val actual = SliderValueToTimeElements().getMinutesOf(toConvert)
        val expected = 20L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_99_is_59() {
        val toConvert = 12.99f
        val actual = SliderValueToTimeElements().getMinutesOf(toConvert)
        val expected = 59L
        assertEquals(expected, actual)
    }

    @Test
    fun getMinutesOf_100_is_0() {
        val toConvert = 12.0f
        val actual = SliderValueToTimeElements().getMinutesOf(toConvert)
        val expected = 0L
        assertEquals(expected, actual)
    }

    @Test
    fun getNowPlus() {
        val sliderClass = SliderValueToTimeElements()
        val toAdd = 12.10f
        val actual = sliderClass.getNowPlus(toAdd)
        val expected = ZonedDateTime.now()
            .plusHours(sliderClass.getHoursOf(toAdd))
            .plusMinutes(sliderClass.getMinutesOf(toAdd))
        assertEquals(expected.year, actual.year)
        assertEquals(expected.monthValue, actual.monthValue)
        assertEquals(expected.dayOfMonth, actual.dayOfMonth)
        assertEquals(expected.hour, actual.hour)
        assertEquals(expected.minute, actual.minute)
    }

    @Test
    fun getNowPlus_evenAbsurdValues() {
        val sliderClass = SliderValueToTimeElements()
        val toAdd = 1290.99f
        val actual = sliderClass.getNowPlus(toAdd)
        val expected = ZonedDateTime.now()
            .plusHours(sliderClass.getHoursOf(toAdd))
            .plusMinutes(sliderClass.getMinutesOf(toAdd))
        assertEquals(expected.year, actual.year)
        assertEquals(expected.monthValue, actual.monthValue)
        assertEquals(expected.dayOfMonth, actual.dayOfMonth)
        assertEquals(expected.hour, actual.hour)
        assertEquals(expected.minute, actual.minute)
    }
}