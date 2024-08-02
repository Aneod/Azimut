package com.astro.azimut.main.background.locationGetter

import android.app.Activity
import android.content.Intent
import android.location.Location
import android.widget.Toast
import com.astro.azimut.main.ChosenCoordinates
import com.astro.azimut.main.MainActivity
import com.astro.azimut.main.MapScreen
import org.maplibre.android.geometry.LatLng

/**
 * This object sort the location states (OK, null, access denied) and react in function.
 */
object LocationGetter {

    fun tryToGet(activity: Activity) {
        LastKnownLocation().get(activity, object : LastKnownLocation.LocationCallback {
            override fun onLocation(location: Location) {
                update(activity, location)
            }

            override fun onNull() {
                showNullLocationMessage(activity)
            }

            override fun permissionNotGranted() {
                askAuthorizations(activity)
            }
        })
    }

    private fun update(activity: Activity, location: Location) {
        val latLng = LatLng(location.latitude, location.longitude)
        ChosenCoordinates.set(latLng)
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
    }

    private fun showNullLocationMessage(activity: Activity) {
        Toast.makeText(activity, "Invalid location", Toast.LENGTH_SHORT).show()
    }

    private fun askAuthorizations(activity: Activity) {
        RequestLocationPermission().requestLocationPermission(activity)
    }
}
