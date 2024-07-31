package com.astro.azimut.main.background

import android.location.Location
import com.astro.azimut.main.background.locationGetter.LocationGetter
import com.astro.azimut.main.MainActivity
import java.time.ZonedDateTime

object Background {

    fun update(activity: MainActivity, timeToAnalyze: ZonedDateTime) {
        CurrentAnalyzedTime.setDateTime(timeToAnalyze)
        tryToGetLocation(activity)
    }

    fun tryToGetLocation(activity: MainActivity) {
        LocationGetter.tryToGet(activity)
    }

    fun apply(location: Location) {
        SunElevation().get(location, CurrentAnalyzedTime.getDateTime())
    }

    fun makeNeutral() {

    }

}