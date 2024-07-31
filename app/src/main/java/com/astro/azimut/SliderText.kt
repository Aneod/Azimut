package com.astro.azimut

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import kotlin.math.floor

@RequiresApi(Build.VERSION_CODES.O)
class SliderText {

    fun getBonusHours(value: Float): Long {
        return floor(value.toDouble()).toLong()
    }

    fun getBonusMinutes(value: Float): Long {
        val floatMinutes = value % 1.0f
        return (floatMinutes * 60).toLong()
    }

    private fun getTimeText(hour: Long, minutes: Long): String {
        val increasedMinutes = minutes % 60
        val oneCharMinutes = increasedMinutes > -10 && increasedMinutes < 10
        if(oneCharMinutes) {
            return "${hour}h0$increasedMinutes"
        }
        return "${hour}h$increasedMinutes"
    }

    private fun getTimeTextLimitedHours(hour: Long, minutes: Long): String {
        val increasedHour = hour % 24
        return getTimeText(increasedHour, minutes)
    }

    private fun getBonusTimeText(value: Float): String {
        val hour = getBonusHours(value)
        val minutes = getBonusMinutes(value)
        return getTimeText(hour, minutes)
    }

    private fun getTargetTimeText(value: Float): String {
        val targetTime = ZonedDateTime.now()
        val hour = targetTime.hour + getBonusHours(value)
        val minutes = targetTime.minute + getBonusMinutes(value)
        val howManyPassedHours = minutes / 60
        val correctedHour = hour + howManyPassedHours
        return getTimeTextLimitedHours(correctedHour, minutes)
    }

    fun getSliderText(value: Float): String {
        val targetTime = getTargetTimeText(value)
        val plusTime = getBonusTimeText(value)
        return "$targetTime (+$plusTime)"
    }

}