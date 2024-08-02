package com.astro.azimut.main.background

import net.e175.klaus.solarpositioning.DeltaT
import net.e175.klaus.solarpositioning.SPA
import net.e175.klaus.solarpositioning.SolarPosition
import org.maplibre.android.geometry.LatLng
import java.time.ZonedDateTime

class SunElevation {

    fun get(location: LatLng, dateTime: ZonedDateTime): Double {

        val latitude = location.latitude
        val longitude = location.longitude

        val position: SolarPosition = SPA.calculateSolarPosition(
            dateTime,
            latitude,
            longitude,
            0.0,
            DeltaT.estimate(dateTime.toLocalDate()),
            1000.0,  // reasonable default.
            15.0 // default value.
        )

        val zenithAngle = position.zenithAngle
        val elevationAngle = 90.0 - zenithAngle

        println("Sun elevation at ${dateTime.hour}h${dateTime.minute} : $elevationAngle°")
        return elevationAngle
    }
}