package com.astro.azimut

import android.location.Location
import net.e175.klaus.solarpositioning.DeltaT
import net.e175.klaus.solarpositioning.SPA
import net.e175.klaus.solarpositioning.SolarPosition
import java.time.ZonedDateTime

class SunElevation {

    fun sunElevation(location: Location, dateTime: ZonedDateTime) {

        val latitude = location.latitude
        val longitude = location.longitude
        val elevation = location.altitude

        val position: SolarPosition = SPA.calculateSolarPosition(
            dateTime,
            latitude,
            longitude,
            elevation,
            DeltaT.estimate(dateTime.toLocalDate()),
            1000.0, // reasonable default.
            15.0 // default value.
        )

        val zenithAngle = position.zenithAngle
        val elevationAngle = 90.0 - zenithAngle

        println("Sun elevation at ${dateTime.hour}h${dateTime.minute} : $elevationAngle°")
    }
}