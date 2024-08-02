package com.astro.azimut.main.background.locationGetter

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.app.Activity
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.astro.azimut.main.MainActivity

class RequestLocationPermission {

    private val requestLocationPermission = 1

    fun requestLocationPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(ACCESS_FINE_LOCATION),
            requestLocationPermission
        )
    }

    fun onRequestPermissionsResult(activity: Activity, requestCode: Int, grantResults: IntArray) {
        if (requestCode == requestLocationPermission) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LocationGetter.tryToGet(activity)
            }
        }
    }
}