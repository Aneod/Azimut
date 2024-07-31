package com.astro.azimut

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.O)
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
            TryToUseLocation.tryToUseLocation(activity)
        }
    }

    fun onRequestPermissionsResult(activity: MainActivity, requestCode: Int, grantResults: IntArray) {
        if (requestCode == requestLocationPermission) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                TryToUseLocation.tryToUseLocation(activity)
            } else {
                Toast.makeText(activity, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}