package com.astro.azimut.main.background.locationGetter

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.astro.azimut.main.background.Background
import com.astro.azimut.main.MainActivity

class RequestLocationPermission {

    private val requestLocationPermission = 1

    fun requestLocationPermission(activity: MainActivity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(ACCESS_FINE_LOCATION),
            requestLocationPermission
        )
    }

    fun onRequestPermissionsResult(activity: MainActivity, requestCode: Int, grantResults: IntArray) {
        if (requestCode == requestLocationPermission) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Background.tryToGetLocation(activity)
            } else {
                Toast.makeText(activity, "Authorisation denied", Toast.LENGTH_SHORT).show()
                Background.makeNeutral()
            }
        }
    }
}