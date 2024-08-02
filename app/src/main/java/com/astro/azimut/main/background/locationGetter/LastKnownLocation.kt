package com.astro.azimut.main.background.locationGetter

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.content.ContextCompat
import com.astro.azimut.main.MainActivity
import com.google.android.gms.location.LocationServices

class LastKnownLocation {

    /**
     * Checks the user phone location state and returns it.
     */
    fun get(activity: Activity, callback: LocationCallback) {
        val selfPermission = ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
        if (selfPermission == PackageManager.PERMISSION_GRANTED) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.lastLocation.addOnSuccessListener(activity) { location ->
                if (location != null) {
                    callback.onLocation(location)
                }
                else callback.onNull()
            }
        } else {
            callback.permissionNotGranted()
        }
    }

    interface LocationCallback {
        fun onLocation(location: Location)
        fun onNull()
        fun permissionNotGranted()
    }

}