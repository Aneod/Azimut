package com.astro.azimut.main.background

import java.time.ZonedDateTime

object CurrentAnalyzedTime {

    private var dateTime: ZonedDateTime = ZonedDateTime.now()

    fun getDateTime(): ZonedDateTime {
        return dateTime
    }

    fun setDateTime(dateTime: ZonedDateTime) {
        CurrentAnalyzedTime.dateTime = dateTime
    }


    private var sliderValue: Float = 0.0f

    fun getSliderValue(): Float {
        return sliderValue
    }

    fun setSliderValue(sliderValue: Float) {
        this.sliderValue = sliderValue
    }

}