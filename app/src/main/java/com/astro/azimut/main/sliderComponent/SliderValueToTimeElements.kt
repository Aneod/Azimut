package com.astro.azimut.main.sliderComponent

import java.time.ZonedDateTime
import kotlin.math.floor

/**
 * Take a Float (Slider value) who represents hour and minutes.
 * Hour is the floor part.
 * And the minutes are the decimal part.
 * <br>
 * For example : 2.345 => 2 hours and 21 (0.345 * 60) minutes.
 */
class SliderValueToTimeElements {

    fun getHoursOf(sliderValue: Float): Long {
        return floor(sliderValue.toDouble()).toLong()
    }

    fun getMinutesOf(sliderValue: Float): Long {
        val floatMinutes = sliderValue % 1.0f
        return (floatMinutes * 60).toLong()
    }

    fun getNowPlus(sliderValue: Float): ZonedDateTime {
        return ZonedDateTime.now()
            .plusHours(getHoursOf(sliderValue))
            .plusMinutes(getMinutesOf(sliderValue))
    }

}
