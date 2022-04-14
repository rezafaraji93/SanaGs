package com.faraji.sanags.feature_get_info.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.sanags.R
import com.faraji.sanags.core.presentation.ui.theme.Green
import com.faraji.sanags.core.presentation.util.UiEvent
import com.faraji.sanags.core.presentation.util.asString
import com.faraji.sanags.feature_get_info.presentation.components.AddressItem
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GetInfoScreen(
    onNavigate: (String) -> Unit,
    scaffoldState: ScaffoldState,
    viewModel: GetInfoViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {

            if (!state.isLoading && state.addressList.isEmpty()) {

                Button(
                    onClick = {
                        viewModel.getAddressList()
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Green,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text(
                        text = stringResource(id = R.string.tryAgain),
                        style = MaterialTheme.typography.body2
                    )
                }

            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                items(
                    items = state.addressList
                ) { items ->
                    AddressItem(item = items)
                }

            }

            ExtendedFloatingActionButton(
                text = {
                    Text(
                        text = stringResource(id = R.string.address1),
                        style = MaterialTheme.typography.body2
                    )
                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = stringResource(id = R.string.add)
                    )
                },
                onClick = { viewModel.onAddAddressClicked() },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
            )

        }
    }


}