package com.astro.azimut.main.sliderComponent

import android.util.Log
import com.astro.azimut.main.ChosenCoordinates
import com.astro.azimut.main.TimezoneMapper
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class SliderText {

    private fun getTimeText(hour: Long, minutes: Long): String {
        val oneCharMinutes = minutes > -10 && minutes < 10
        if(oneCharMinutes) {
            return "${hour}h0$minutes"
        }
        return "${hour}h$minutes"
    }

    private fun getBonusTimeText(bonusHour: Long, bonusMinutes: Long): String {
        return getTimeText(bonusHour, bonusMinutes)
    }

    private fun getRealisticTime(hour: Long, minutes: Long): Pair<Long, Long> {
        val howManyPassedHours = minutes / 60
        val hoursPlusPassedHours = hour + howManyPassedHours
        val increasedHours = hoursPlusPassedHours % 24
        val increasedMinutes = minutes % 60
        return Pair(increasedHours, increasedMinutes)
    }

    private fun getLocalTime(): LocalDateTime {
        val resultTimeZone = TimezoneMapper.latLngToTimezoneString(
            ChosenCoordinates.getLatitude(),
            ChosenCoordinates.getLongitude()
        )
        val zoneId = ZoneId.of(resultTimeZone)
        val currentInstant = Instant.now()
        val localDateTime = LocalDateTime.ofInstant(currentInstant, zoneId)
        return localDateTime
    }

    private fun getTargetTimeText(bonusHour: Long, bonusMinutes: Long): String {
        val targetTime = getLocalTime()
        val hour = targetTime.hour + bonusHour
        val minutes = targetTime.minute + bonusMinutes
        val realisticTime = getRealisticTime(hour, minutes)
        return getTimeText(realisticTime.first, realisticTime.second)
    }

    /**
     * The slider text is something like '14h08 (+3h12)'.
     * <br>
     * With the current time and a given bonus hour and minutes, this method print the current
     * time PLUS the bonus time (like current:10h56 and bonus:3h12 => 14h08) and the bonus time.
     * This method take just a float who is the bonus time.
     * @see SliderValueToTimeElements
     */
    fun getSliderText(sliderValue: Float): String {
        val bonusHour = SliderValueToTimeElements().getHoursOf(sliderValue)
        val bonusMinutes = SliderValueToTimeElements().getMinutesOf(sliderValue)
        val targetTime = getTargetTimeText(bonusHour, bonusMinutes)
        val bonusTime = getBonusTimeText(bonusHour, bonusMinutes)
        return "$targetTime (in $bonusTime)"
    }

}