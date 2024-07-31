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

}