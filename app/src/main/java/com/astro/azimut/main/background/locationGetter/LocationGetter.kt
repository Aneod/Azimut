package com.astro.azimut.main.background.locationGetter

import android.location.Location
import android.widget.Toast
import com.astro.azimut.main.background.Background
import com.astro.azimut.main.MainActivity

/**
 * This object sort the location states (OK, null, access denied) and react in function.
 */
object LocationGetter {

    fun tryToGet(activity: MainActivity) {
        LastKnownLocation().get(activity, object : LastKnownLocation.LocationCallback {
            override fun onLocation(location: Location) {
                update(location)
            }

            override fun onNull() {
                showNullLocationMessage(activity)
            }

            override fun permissionNotGranted() {
                askAuthorizations(activity)
            }
        })
    }

    private fun update(location: Location) {
        Background.apply(location)
    }

    private fun showNullLocationMessage(activity: MainActivity) {
        Toast.makeText(activity, "Invalid location", Toast.LENGTH_SHORT).show()
        Background.makeNeutral()
    }

    private fun askAuthorizations(activity: MainActivity) {
        RequestLocationPermission().requestLocationPermission(activity)
    }
}
