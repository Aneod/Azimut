package com.astro.azimut.main

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.astro.azimut.main.background.locationGetter.LocationGetter
import com.astro.azimut.ui.theme.AzimutTheme
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer
import org.maplibre.android.annotations.Marker
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.MapView

class MapActivity : ComponentActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        MapLibre.getInstance(
            this,
            null,
            WellKnownTileServer.MapLibre
        )

        setContent {
            AzimutTheme {
                MapScreen(this)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        if (::mapView.isInitialized) {
            mapView.onStart()
        }
    }

    override fun onResume() {
        super.onResume()
        if (::mapView.isInitialized) {
            mapView.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (::mapView.isInitialized) {
            mapView.onPause()
        }
    }

    override fun onStop() {
        super.onStop()
        if (::mapView.isInitialized) {
            mapView.onStop()
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (::mapView.isInitialized) {
            mapView.onLowMemory()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mapView.isInitialized) {
            mapView.onDestroy()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::mapView.isInitialized) {
            mapView.onSaveInstanceState(outState)
        }
    }
}

@Composable
fun MapScreen(activity: MapActivity) {

    val context = LocalContext.current
    var mapView by remember { mutableStateOf<MapView?>(null) }
    var marker by remember { mutableStateOf<Marker?>(null) }

    fun setCoordinates(mapLibre: MapLibreMap, latLng: LatLng) {
        marker?.remove()
        ChosenCoordinates.set(latLng)
        marker = mapLibre.addMarker(MarkerOptions().position(latLng))
    }

    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                MapView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    getMapAsync { map ->
                        map.setStyle("https://demotiles.maplibre.org/style.json") {
                            map.cameraPosition = CameraPosition.Builder()
                                .target(ChosenCoordinates.get())
                                .zoom(4.0)
                                .build()
                        }
                        map.uiSettings.isRotateGesturesEnabled = false

                        marker = map.addMarker(
                            MarkerOptions()
                            .position(ChosenCoordinates.get())
                        )

                        map.addOnMapClickListener { latLng ->
                            setCoordinates(map, latLng)
                            true
                        }
                    }
                }.also {
                    mapView = it
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Text(text = "Done")
        }

        Button(
            onClick = {
                LocationGetter.tryToGet(activity)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Use my position")
        }
    }
}
