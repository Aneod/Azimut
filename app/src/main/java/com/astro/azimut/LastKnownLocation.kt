package com.astro.azimut

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.location.Location
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices

class LastKnownLocation {

    fun get(activity: MainActivity, callback: LocationCallback) {
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