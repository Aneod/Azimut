package com.astro.azimut.main

import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel

class SunPositionViewModel : ViewModel() {
    var xPercent = mutableFloatStateOf(0.5f)
    var yPercent = mutableFloatStateOf(0.3f)

    fun setPosition(x: Float, y: Float) {
        xPercent.floatValue = x.coerceIn(0f, 1f)
        yPercent.floatValue = y.coerceIn(0f, 1f)
    }
}
