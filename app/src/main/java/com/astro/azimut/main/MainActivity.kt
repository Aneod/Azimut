package com.astro.azimut.main

import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.astro.azimut.main.background.locationGetter.RequestLocationPermission
import com.astro.azimut.main.buttonLocation.ButtonLocation
import com.astro.azimut.main.sliderComponent.SliderComponent
import com.astro.azimut.ui.theme.AzimutTheme
import org.mapsforge.core.model.LatLong
import org.mapsforge.map.android.graphics.AndroidGraphicFactory
import org.mapsforge.map.android.util.AndroidUtil
import org.mapsforge.map.android.view.MapView
import org.mapsforge.map.layer.cache.TileCache
import org.mapsforge.map.layer.renderer.TileRendererLayer
import org.mapsforge.map.reader.MapFile
import org.mapsforge.map.rendertheme.InternalRenderTheme
import java.io.File

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AzimutTheme {
                Scaffold { innerPadding ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        MapScreen()
                    }
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

    @Deprecated("")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RequestLocationPermission().onRequestPermissionsResult(this, requestCode, grantResults)
    }
}

@Composable
fun MapScreen() {
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        var mapView: MapView? = null

        AndroidView(factory = {
            mapView = MapView(context).apply {
                layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)

                val tileCache: TileCache = AndroidUtil.createTileCache(
                    context,
                    "mapcache",
                    model.displayModel.tileSize,
                    1f,
                    model.frameBufferModel.overdrawFactor
                )

                val mapFile = File(context.getExternalFilesDir(null), "germany.map")
                val mapFileReader = MapFile(mapFile)

                val tileRendererLayer = TileRendererLayer(
                    tileCache,
                    mapFileReader,
                    model.mapViewPosition,
                    AndroidGraphicFactory.INSTANCE
                ).apply {
                    setXmlRenderTheme(InternalRenderTheme.DEFAULT)
                }

                layerManager.layers.add(tileRendererLayer)
                setCenter(LatLong(51.0, 9.0))
                setZoomLevel((12).toByte())
            }

            mapView!!
        }, update = {
            mapView?.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        })

        Button(onClick = {
            mapView?.visibility = View.VISIBLE
        }) {
            Text(text = "Open Map")
        }
    }
}