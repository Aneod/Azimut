package com.astro.azimut.main.sliderComponent

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import java.time.ZonedDateTime

class SliderTextTest {

    private var now: ZonedDateTime = ZonedDateTime.now()

    @Before
    fun setUp() {
        now = ZonedDateTime.now()
    }

    private fun addZero(minutes: Long): String {
        val oneCharMinutes = minutes > -10 && minutes < 10
        if(oneCharMinutes) {
            return "0$minutes"
        }
        return "$minutes"
    }

    private fun isEquals(zonedDateTime: ZonedDateTime, float: Float): Boolean {
        val nowHour = zonedDateTime.hour
        val nowMinutes = zonedDateTime.minute
        val actual = SliderText().getSliderText(float)
        val bonusHour = SliderValueToTimeElements().getHoursOf(float)
        val bonusMinutes = SliderValueToTimeElements().getMinutesOf(float)
        val targetHour = nowHour + bonusHour
        val targetMinutes = nowMinutes + bonusMinutes
        val howManyHoursPassed = targetMinutes / 60
        val correctedHour = (targetHour + howManyHoursPassed) % 24
        val correctedMinutes = targetMinutes % 60
        val targetTime = "${correctedHour}h${addZero(correctedMinutes)}"
        val bonusTime = "${bonusHour}h${addZero(bonusMinutes)}"
        val expected = "$targetTime (in $bonusTime)"
        return expected == actual
    }

    @Test
    fun getSliderText() {
        val toAdd = 3.50f
        assertTrue(isEquals(now, toAdd))
    }

    @Test
    fun getSliderText_0() {
        val toAdd = 0f
        assertTrue(isEquals(now, toAdd))
    }

    @Test
    fun getSliderText_1() {
        val toAdd = 1f
        assertTrue(isEquals(now, toAdd))
    }

    @Test
    fun getSliderText_absurd() {
        val toAdd = 45365.23f
        assertTrue(isEquals(now, toAdd))
    }
}