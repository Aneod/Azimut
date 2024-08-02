package com.astro.azimut.main

import org.maplibre.android.geometry.LatLng

object ChosenCoordinates {

    private val montparnasseTowerCoordinates = LatLng(48.842106, 2.321782)
    private var latitude = montparnasseTowerCoordinates.latitude
    private var longitude = montparnasseTowerCoordinates.longitude

    fun get(): LatLng {
        return LatLng(latitude, longitude)
    }

    override fun toString(): String {
        return "$latitude, $longitude"
    }

    fun getLatitude(): Double {
        return latitude
    }

    fun getLongitude(): Double {
        return longitude
    }

    fun set(latLng: LatLng) {
        latitude = latLng.latitude
        longitude = latLng.longitude
    }

}