package com.astro.azimut

import java.time.ZonedDateTime

class SliderText {

    private fun getTimeText(hour: Long, minutes: Long): String {
        val oneCharMinutes = minutes > -10 && minutes < 10
        if(oneCharMinutes) {
            return "${hour}h0$minutes"
        }
        return "${hour}h$minutes"
    }

    private fun getBonusTimeText(bonusHour: Long, bonusMinutes: Long): String {
        val bonusTime = getTimeText(bonusHour, bonusMinutes)
        return "(+$bonusTime)"
    }

    private fun getRealisticTime(hour: Long, minutes: Long): Pair<Long, Long> {
        val howManyPassedHours = minutes / 60
        val hoursPlusPassedHours = hour + howManyPassedHours
        val increasedHours = hoursPlusPassedHours % 24
        val increasedMinutes = minutes % 60
        return Pair(increasedHours, increasedMinutes)
    }

    private fun getTargetTimeText(bonusHour: Long, bonusMinutes: Long): String {
        val targetTime = ZonedDateTime.now()
        val hour = targetTime.hour + bonusHour
        val minutes = targetTime.minute + bonusMinutes
        val realisticTime = getRealisticTime(hour, minutes)
        return getTimeText(realisticTime.first, realisticTime.second)
    }

    /**
     * The slider is something like '14h08 (+3h12)'.
     * <br>
     * With the current time and a given bonus hour and minutes, this method print the current
     * time PLUS the bonus time (like current:10h56 and bonus:3h12 => 14h08) and the bonus time.
     * This method take just a float who is the bonus time.
     * @see FloatToTimeElements
     */
    fun getSliderText(value: Float): String {
        val bonusHour = FloatToTimeElements().getHoursOf(value)
        val bonusMinutes = FloatToTimeElements().getMinutesOf(value)
        val targetTime = getTargetTimeText(bonusHour, bonusMinutes)
        val bonusTime = getBonusTimeText(bonusHour, bonusMinutes)
        return "$targetTime $bonusTime"
    }

}