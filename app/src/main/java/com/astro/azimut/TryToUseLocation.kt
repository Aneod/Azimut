package com.astro.azimut

import android.location.Location
import android.widget.Toast
import java.time.ZonedDateTime

object TryToUseLocation {

    private var dateTime: ZonedDateTime = ZonedDateTime.now()

    fun setDateTime(dateTime: ZonedDateTime) {
        this.dateTime = dateTime
    }

    fun tryToUseLocation(activity: MainActivity) {
        LastKnownLocation().get(activity, object : LastKnownLocation.LocationCallback {
            override fun onLocation(location: Location) {
                update(location)
            }

            override fun onNull() {
                showNullLocationMessage(activity)
            }

            override fun permissionNotGranted() {
                requestLocationPermission(activity)
            }
        })
    }

    private fun update(location: Location) {
        SunElevation().sunElevation(location, dateTime)
    }

    private fun showNullLocationMessage(activity: MainActivity) {
        Toast.makeText(activity, "Invalid location", Toast.LENGTH_SHORT).show()
    }

    private fun requestLocationPermission(activity: MainActivity) {
        RequestLocationPermission().requestLocationPermission(activity)
    }
}