package com.dicoding.etrash.ui.screen.location

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class LocationScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapsScreen()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapsScreen() {
    val mapViewContainer by remember { mutableStateOf<MapViewContainer?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Google Maps") }
            )
        }
    ) {
        mapViewContainer?.MapViewContainerContent()
    }
}

class MapViewContainer {
    @Composable
    fun MapViewContainerContent() {
        val mapView = rememberMapViewWithLifecycle()

        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
            update = { mapView ->
                mapView.getMapAsync { googleMap ->
                    onMapReady(googleMap)
                }
            }
        )
    }

    private fun onMapReady(googleMap: GoogleMap) {
        val dicodingSpace = LatLng(-6.8957643, 107.6338462)
        googleMap.addMarker(
            MarkerOptions()
                .position(dicodingSpace)
                .title("Dicoding Space")
                .snippet("Batik Kumeli No.50")
        )
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dicodingSpace, 15f))
    }
}

@Composable
fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    return remember(context) {
        MapView(context).apply {
            id = android.view.View.generateViewId()
        }
    }
}