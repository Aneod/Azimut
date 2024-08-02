package com.astro.azimut.main.sliderComponent

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
import com.astro.azimut.main.ChosenCoordinates
import com.astro.azimut.main.MainActivity
import com.astro.azimut.main.background.CurrentAnalyzedTime
import com.astro.azimut.main.background.SunElevation

/**
 * This class must be take just a slider and its visual properties plus its slide effect. NO MORE.
 */
@Composable
fun SliderComponent() {

    val context = LocalContext.current
    var sliderPosition by remember { mutableFloatStateOf(0f) }

    fun onValueChange(value: Float) {
        if(context is MainActivity) {
            val timeToAnalyze = SliderValueToTimeElements().getNowPlus(value)
            CurrentAnalyzedTime.setDateTime(timeToAnalyze)

            val latLng = ChosenCoordinates.get()
            val dateTime = CurrentAnalyzedTime.getDateTime()
            val elevation = SunElevation().get(latLng, dateTime)

            val floatHour = timeToAnalyze.hour + timeToAnalyze.minute / 60.0f
            context.updateSunPosition(floatHour, elevation)
        }
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
            onValueChange = { sliderPosition = it; onValueChange(sliderPosition) },
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