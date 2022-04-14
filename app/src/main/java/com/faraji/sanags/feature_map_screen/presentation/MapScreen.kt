package com.faraji.sanags.feature_map_screen.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.sanags.R
import com.faraji.sanags.core.presentation.ui.theme.Green
import com.faraji.sanags.core.presentation.util.Screen
import com.faraji.sanags.core.presentation.util.UiEvent
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapScreen(
    onVerifyLocationClicked: (LatLng) -> Unit
) {

    val tehran = LatLng(35.6, 51.3)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(tehran, 10f)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = MapUiSettings(
                myLocationButtonEnabled = false
            ),
            cameraPositionState = cameraPositionState
        )
        IconButton(
            onClick = {

            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_map_marker),
                contentDescription = stringResource(
                    id = R.string.mapMarker
                )
            )
        }
        Button(
            onClick = {
                onVerifyLocationClicked(
                    LatLng(
                        cameraPositionState.position.target.latitude,
                        cameraPositionState.position.target.longitude
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Green,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = stringResource(id = R.string.verifyLocation),
                style = MaterialTheme.typography.body1
            )
        }
    }
}