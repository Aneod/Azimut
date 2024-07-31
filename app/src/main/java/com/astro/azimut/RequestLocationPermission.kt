package com.astro.azimut

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class RequestLocationPermission {

    private val requestLocationPermission = 1

    fun requestLocationPermission(activity: MainActivity) {
        val selfPermission = ContextCompat.checkSelfPermission(activity, ACCESS_FINE_LOCATION)
        if (selfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(ACCESS_FINE_LOCATION),
                requestLocationPermission
            )
        } else {
            activity.tryToUseLocation()
        }
    }

    fun onRequestPermissionsResult(activity: MainActivity, requestCode: Int, grantResults: IntArray) {
        if (requestCode == requestLocationPermission) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                activity.tryToUseLocation()
            } else {
                Toast.makeText(activity, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

}