package com.astro.azimut.main

import android.os.Bundle
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
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
import com.astro.azimut.main.background.locationGetter.RequestLocationPermission
import com.astro.azimut.main.sliderComponent.SliderComponent
import com.astro.azimut.ui.theme.AzimutTheme
import org.maplibre.android.MapLibre
import org.maplibre.android.WellKnownTileServer
import org.maplibre.android.annotations.MarkerOptions
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapView

class MainActivity : ComponentActivity() {

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
                Scaffold { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        Spacer(modifier = Modifier.weight(1f))
                        SliderComponent()
                        MapScreen { initializedMapView ->
                            mapView = initializedMapView
                        }
                    }
                }
            }
        }
    }

    @Deprecated("")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RequestLocationPermission().onRequestPermissionsResult(this, requestCode, grantResults)
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
fun MapScreen(onMapViewInitialized: (MapView) -> Unit) {

    val context = LocalContext.current
    var mapView by remember { mutableStateOf<MapView?>(null) }
    val montparnasseTowerCoordinates = LatLng(48.842106, 2.321782)
    val marker: MarkerOptions = MarkerOptions()
        .position(montparnasseTowerCoordinates)
        .title("Marker")


    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            factory = {
                MapView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    getMapAsync { map ->
                        map.setStyle("https://demotiles.maplibre.org/style.json") {
                            map.cameraPosition = CameraPosition.Builder()
                                .target(montparnasseTowerCoordinates)
                                .zoom(4.0)
                                .build()
                        }
                        map.addMarker(marker)

                        map.addOnMapClickListener { latLng ->
                            marker.position(latLng)
                            val latitude = latLng.latitude
                            val longitude = latLng.longitude
                            Toast.makeText(context, "Clicked: Lat $latitude, Lon $longitude", Toast.LENGTH_SHORT).show()
                            true
                        }
                    }
                }.also {
                    mapView = it
                    onMapViewInitialized(it)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        Button(
            onClick = {
                // Handle button click to open or interact with the map
            },
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp)
        ) {
            Text(text = "Open Map")
        }
    }
}