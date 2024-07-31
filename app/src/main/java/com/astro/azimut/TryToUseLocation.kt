package com.astro.azimut

import android.location.Location
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime

@RequiresApi(Build.VERSION_CODES.O)
class TryToUseLocation {

    private var dateTime: ZonedDateTime = ZonedDateTime.now()

    fun getDateTime(): ZonedDateTime {
        return dateTime
    }

    fun setDateTime(dateTime: ZonedDateTime) {
        this.dateTime = dateTime
    }

    fun tryToUseLocation(activity: MainActivity) {
        LastKnownLocation().get(activity, object : LastKnownLocation.LocationCallback {
            @RequiresApi(Build.VERSION_CODES.O)
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

    @RequiresApi(Build.VERSION_CODES.O)
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