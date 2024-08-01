package com.astro.azimut.main.buttonLocation

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.astro.azimut.main.MainActivity

@Composable
fun ButtonLocation() {

    val context = LocalContext.current

    @SuppressLint("QueryPermissionsNeeded")
    fun openMap(activity: MainActivity) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=world map")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        if (mapIntent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(mapIntent)
        }
    }

    Button(
        onClick = {
            if (context is MainActivity) {
                openMap(context)
            }
        }
    ) {
        Text("Mon Bouton")
    }

}

@Preview(showBackground = true)
@Composable
fun ButtonLocationPreview() {
    ButtonLocation()
}