package com.faraji.sanags.feature_set_info.presentation.components

import androidx.compose.runtime.Composable
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun MapMarker(
    markerState: MarkerState
) {
    Marker(
        state = markerState,
    )
}

