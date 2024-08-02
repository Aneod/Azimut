package com.astro.azimut.main

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ElevationChart(height: Dp, xPercent: Float, yPercent: Float) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawBackground(Color.Blue)
            drawGround()
            val canvasWidth = size.width
            val canvasHeight = size.height
            val xPosition = xPercent * canvasWidth
            val yPosition = canvasHeight - (yPercent * canvasHeight)
            drawSun(xPosition, yPosition)
        }
    }
}

fun DrawScope.drawBackground(color: Color) {
    drawRect(color = color, size = size)  // Draw a rectangle with the background color
}

fun DrawScope.drawGround() {
    drawLine(
        color = Color.Black,
        start = Offset(0f, size.height),
        end = Offset(size.width, size.height),
        strokeWidth = 20f
    )
}

fun DrawScope.drawSun(x: Float, y: Float) {
    drawCircle(
        color = Color.Yellow,
        radius = 40f,
        center = Offset(x, y)
    )
}

@Preview
@Composable
fun PreviewElevationChart() {
    ElevationChart(250.dp, .5f, .5f)
}
