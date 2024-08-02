package com.astro.azimut.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.astro.azimut.main.sliderComponent.SliderComponent
import com.astro.azimut.ui.theme.AzimutTheme
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer

class MainActivity : ComponentActivity() {

    private lateinit var sunPositionViewModel: SunPositionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        MapLibre.getInstance(
            this,
            null,
            WellKnownTileServer.MapLibre
        )

        sunPositionViewModel = ViewModelProvider(this)[SunPositionViewModel::class.java]

        setContent {
            AzimutTheme {
                Scaffold { innerPadding ->

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    ) {
                        OpenMapButton()
                        ElevationChart(
                            250.dp,
                            sunPositionViewModel.xPercent.floatValue,
                            sunPositionViewModel.yPercent.floatValue
                        )
                        SliderComponent()
                    }
                }
            }
        }
        updateSunPosition()
    }

    fun updateSunPosition() {
        sunPositionViewModel.setPosition()
    }
}

@Composable
fun OpenMapButton() {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(context, MapActivity::class.java)
            context.startActivity(intent)
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(text = ChosenCoordinates.toString())
    }
}
