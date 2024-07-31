package com.astro.azimut

import kotlin.math.floor

/**
 * Take a Float who represents hour and minutes. Hour is the floor part. And the minutes are the
 * decimal part.
 * <br>
 * For example : 2.345 => 2 hours and 21 (0.345 * 60) minutes.
 */
class FloatToTimeElements {

    fun getHoursOf(value: Float): Long {
        return floor(value.toDouble()).toLong()
    }

    fun getMinutesOf(value: Float): Long {
        val floatMinutes = value % 1.0f
        return (floatMinutes * 60).toLong()
    }

}