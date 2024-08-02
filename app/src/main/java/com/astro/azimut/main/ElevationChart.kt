package com.astro.azimut.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val sunRadius = 20f

@Composable
fun ElevationChart(height: Dp, xPercent: Float, yPercent: Float) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawBackground(yPercent)
            val canvasWidth = size.width
            val canvasHeight = size.height
            val xPosition = xPercent * canvasWidth
            val yPosition = canvasHeight - (yPercent * canvasHeight)

            val clipPath = Path().apply {
                moveTo(0f, canvasHeight)
                lineTo(canvasWidth, canvasHeight)
                lineTo(canvasWidth, 0f)
                lineTo(0f, 0f)
                close()
            }

            clipPath(clipPath) {
                drawSun(xPosition, yPosition)
            }
        }
    }
}

fun DrawScope.drawBackground(sunElevation: Float) {
    val color = SkyColor().getBySunElevation(sunElevation)
    drawRect(color = color, size = size)
}

fun DrawScope.drawSun(x: Float, y: Float) {
    drawCircle(
        color = Color.Yellow,
        radius = sunRadius,
        center = Offset(x, y)
    )
}

@Preview
@Composable
fun PreviewElevationChart() {
    ElevationChart(250.dp, .5f, .5f)
}
