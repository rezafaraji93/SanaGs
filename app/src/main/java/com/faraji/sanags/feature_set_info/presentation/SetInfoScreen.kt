package com.faraji.sanags.feature_set_info.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.faraji.sanags.R
import com.faraji.sanags.core.presentation.components.CustomTextField
import com.faraji.sanags.core.presentation.ui.theme.Green
import com.faraji.sanags.core.presentation.util.UiEvent
import com.faraji.sanags.core.presentation.util.asString
import com.faraji.sanags.feature_map_screen.presentation.MapScreen
import com.faraji.sanags.feature_set_info.util.InfoError
import kotlinx.coroutines.flow.collectLatest

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun SetInfoScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit,
    onPopBackStack: () -> Unit,
    viewModel: SetInfoScreenViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current
    val firstNameState = viewModel.firstNameFieldState.value
    val lastNameState = viewModel.lastNameFieldState.value
    val mobileState = viewModel.mobileFieldState.value
    val phoneState = viewModel.phoneFieldState.value
    val addressState = viewModel.addressFieldState.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current


    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event.route)
                }
                UiEvent.NavigateUp -> onPopBackStack()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        if (state.showMap) {
            item {
                MapScreen {
                    viewModel.onEvent(SetInfoScreenEvent.EnteredLatLng(it))
                }
            }
        } else {
            stickyHeader {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { onPopBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.backArrow
                            )
                        )
                    }
                    Text(
                        text = stringResource(id = R.string.register),
                        style = MaterialTheme.typography.h3
                    )
                }
            }

        }


        item {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.enterInfo),
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            CustomTextField(
                text = firstNameState.text,
                onValueChange = {
                    viewModel.onEvent(SetInfoScreenEvent.EnteredFirstName(it))
                },
                textFieldTitle = R.string.name,
                error = when (firstNameState.error) {
                    is InfoError.FieldEmpty -> stringResource(id = R.string.enterName)
                    is InfoError.InputTooShort -> stringResource(id = R.string.enterNameProperly)
                    else -> ""
                },
                isEntryValid = firstNameState.text.length > 2,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                text = lastNameState.text,
                onValueChange = {
                    viewModel.onEvent(SetInfoScreenEvent.EnteredLastName(it))
                },
                textFieldTitle = R.string.lastName,
                error = when (lastNameState.error) {
                    is InfoError.FieldEmpty -> stringResource(id = R.string.enterLastName)
                    is InfoError.InputTooShort -> stringResource(id = R.string.enterNameLastProperly)
                    else -> ""
                },
                isEntryValid = lastNameState.text.length > 2,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                text = mobileState.text,
                onValueChange = {
                    viewModel.onEvent(SetInfoScreenEvent.EnteredMobile(it))
                },
                isEntryValid = mobileState.text.length == 11,
                textFieldTitle = R.string.mobileNumber,
                error = when (mobileState.error) {
                    is InfoError.FieldEmpty -> stringResource(id = R.string.enterMobile)
                    is InfoError.InputTooShort -> stringResource(id = R.string.enterMobileProperly)
                    else -> ""
                },
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                text = phoneState.text,
                onValueChange = {
                    viewModel.onEvent(SetInfoScreenEvent.EnteredPhone(it))
                },
                textFieldTitle = R.string.phoneNumber,
                error = when (phoneState.error) {
                    is InfoError.FieldEmpty -> stringResource(id = R.string.enterphone)
                    is InfoError.InputTooShort -> stringResource(id = R.string.enterPhoneProperly)
                    else -> ""
                },
                isEntryValid = phoneState.text.length > 8,
                imeAction = ImeAction.Next,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            CustomTextField(
                text = addressState.text,
                onValueChange = {
                    viewModel.onEvent(SetInfoScreenEvent.EnteredAddress(it))
                },
                singleLine = false,
                maxLines = 3,
                isEntryValid = addressState.text.length > 5,
                textFieldTitle = R.string.address,
                error = when (addressState.error) {
                    is InfoError.FieldEmpty -> stringResource(id = R.string.enterAddress)
                    is InfoError.InputTooShort -> stringResource(id = R.string.enterAddressProperly)
                    else -> ""
                },
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(
                            1.dp,
                            MaterialTheme.colors.primary,
                            RoundedCornerShape(4.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    state.tabList.forEach { tab ->
                        Text(
                            text = tab.type,
                            style = MaterialTheme.typography.body2.copy(
                                color = if (tab == state.selectedGender) MaterialTheme.colors.onPrimary else MaterialTheme.colors.onSurface.copy(
                                    0.5f
                                ),
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    if (tab == state.selectedGender) MaterialTheme.colors.primary else MaterialTheme.colors.background
                                )
                                .clickable {
                                    viewModel.onEvent(SetInfoScreenEvent.SelectedGender(tab))
                                }
                                .padding(10.dp)
                                .defaultMinSize(minWidth = 50.dp)
                        )

                    }
                }
                Text(
                    text = stringResource(id = R.string.gender),
                    style = MaterialTheme.typography.body2.copy(
                        color = MaterialTheme.colors.primary
                    )
                )
            }
        }
        item {
            if (state.latLng != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = stringResource(id = R.string.locationSuccessfullyRecieved),
                        style = MaterialTheme.typography.body2.copy(
                            color = Green
                        ),
                        modifier = Modifier
                            .align(Center)
                            .padding(16.dp)
                    )
                }

            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            if (state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            } else {
                Button(
                    onClick = {
                        viewModel.onEvent(SetInfoScreenEvent.NextStepClicked)
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Green,
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Text(
                        text = state.latLng?.let {
                            stringResource(id = R.string.sendData)
                        } ?: stringResource(id = R.string.nextStep),
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        }

    }

}
