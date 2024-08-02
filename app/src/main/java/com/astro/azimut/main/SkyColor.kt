package com.astro.azimut.main

import androidx.compose.ui.graphics.Color

class SkyColor {

    private val maxBlue = 255.0f
    private val maxGreen = 191.0f
    private val blueGreenDiff = maxBlue - maxGreen

    fun getBySunElevation(elevation: Float): Color {
        //val blue = (maxBlue * elevation).coerceIn(0.0f, maxBlue).toInt()
        //val green = (blue - blueGreenDiff).coerceIn(0.0f, maxGreen).toInt()
        return Color(0, maxGreen.toInt(), maxBlue.toInt(), 255)
    }
}