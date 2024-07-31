package com.astro.azimut

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
import com.astro.azimut.ui.theme.AzimutTheme
import java.time.ZonedDateTime

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AzimutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        Spacer(modifier = Modifier.weight(1f))
                        SliderComponent()
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Deprecated("")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RequestLocationPermission().onRequestPermissionsResult(this, requestCode, grantResults)
    }
}

@Preview(showBackground = true)
@Composable
@RequiresApi(Build.VERSION_CODES.O)
fun SliderComponent() {

    val context = LocalContext.current

    fun slideEffect(value: Float): String {
        if(context is MainActivity) {
            val dateTime = ZonedDateTime.now()
                .plusHours(SliderText().getBonusHours(value))
                .plusMinutes(SliderText().getBonusMinutes(value))
            TryToUseLocation.setDateTime(dateTime)
            TryToUseLocation.tryToUseLocation(context)
        }
        return SliderText().getSliderText(value)
    }

    var sliderPosition by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = slideEffect(sliderPosition), // L'effet se lance dès le démarrage.
            fontSize = 20.sp
        )
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            valueRange = 0.0f..24f,
            modifier = Modifier.fillMaxWidth()
        )
    }
}