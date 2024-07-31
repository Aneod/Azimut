package com.astro.azimut

import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults.Thumb
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
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
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        tryToUseLocation()
    }

    fun tryToUseLocation() {
        LastKnownLocation().get(this, object : LastKnownLocation.LocationCallback {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onLocation(location: Location) {
                showLocationInformation(location)
            }

            override fun onNull() {
                showNullLocationMessage()
            }

            override fun permissionNotGranted() {
                requestLocationPermission()
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showLocationInformation(location: Location) {
        val dateTime = ZonedDateTime.now()
        SunElevation().sunElevation(location, dateTime)
    }

    private fun showNullLocationMessage() {
        Toast.makeText(this, "Invalid location", Toast.LENGTH_SHORT).show()
    }

    private fun requestLocationPermission() {
        RequestLocationPermission().requestLocationPermission(this)
    }

    @Deprecated("")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RequestLocationPermission().onRequestPermissionsResult(this, requestCode, grantResults)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AzimutTheme {
        Greeting("Android")
    }
}
