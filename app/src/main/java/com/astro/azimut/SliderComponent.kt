package com.astro.azimut

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.ZonedDateTime

@Composable
fun SliderComponent() {

    val context = LocalContext.current
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    fun slideEffect(value: Float) {
        if(context is MainActivity) {
            val dateTime = ZonedDateTime.now()
                .plusHours(FloatToTimeElements().getHoursOf(value))
                .plusMinutes(FloatToTimeElements().getMinutesOf(value))
            TryToUseLocation.setDateTime(dateTime)
            TryToUseLocation.tryToUseLocation(context)
        }
    }

    fun onValueChange(value: Float) {
        slideEffect(value)
        sliderPosition = value
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = SliderText().getSliderText(sliderPosition),
            fontSize = 20.sp
        )
        Slider(
            value = sliderPosition,
            onValueChange = { onValueChange(it) },
            valueRange = 0.0f..24f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SliderComponentPreview() {
    SliderComponent()
}