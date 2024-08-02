package com.astro.azimut.main

import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import com.astro.azimut.main.background.CurrentAnalyzedTime
import com.astro.azimut.main.background.SunElevation
import net.e175.klaus.solarpositioning.DeltaT
import net.e175.klaus.solarpositioning.SPA
import net.e175.klaus.solarpositioning.SunriseResult
import kotlin.math.cos

class SunPositionViewModel : ViewModel() {

    private val sunXPosMaxValue = .8f

    var xPercent = mutableFloatStateOf(0.5f)
    var yPercent = mutableFloatStateOf(0.5f)

    fun setPosition() {
        val latLng = ChosenCoordinates.get()
        val dateTime = CurrentAnalyzedTime.getDateTime()
        val elevation = SunElevation().get(latLng, dateTime)
        val hour = CurrentAnalyzedTime.getDateTime().hour + CurrentAnalyzedTime.getDateTime().minute / 60.0f
        xPercent.floatValue = hourToPercent(hour)
        yPercent.floatValue = elevationToPercent(elevation)
    }

    /**
     * Take a elevation in degree and returns a Float for set the scheme sun y position.
     * [-90.0, 90.0] -> [-1.0f, 1.0f]
     */
    private fun elevationToPercent(elevation: Double): Float {
        val correctedElevation = elevation.coerceIn(-90.0, 90.0)
        return (correctedElevation / 90.0).toFloat()
    }

    /**
     * Take a hour like 4.50 for 4h30, ans determine the scheme sun x position.
     */
    private fun hourToPercent(hours: Float): Float {

        val dateTime = CurrentAnalyzedTime.getDateTime()
        val latLng = ChosenCoordinates.get()
        val sunriseResult: SunriseResult = SPA.calculateSunriseTransitSet(
            dateTime,
            latLng.latitude,
            latLng.longitude,
            DeltaT.estimate(dateTime.toLocalDate())
        )

        val noonHour = sunriseResult.transit().hour + sunriseResult.transit().minute / 60.0f
        val twilightHour = (noonHour + 6.0f) % 24

        val gapHour = twilightHour - hours
        val degreePerHour = 360 / 24
        val totalDegree = gapHour * degreePerHour
        val totalDegreeInRadians = Math.toRadians(totalDegree.toDouble())
        val pseudoYPos = cos(totalDegreeInRadians) // 0.0f at 14h and 2h. 1.0f at 20h. -1.0f at 8h.

        // -1.0f -> 0.0f
        // 0.0f -> .5f
        // 1.0f -> 1.0f

        val limitedYPos = pseudoYPos * sunXPosMaxValue
        return ((limitedYPos + 1) / 2).toFloat()
    }
}
