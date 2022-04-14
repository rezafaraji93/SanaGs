package com.faraji.sanags.feature_set_info.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faraji.sanags.R
import com.faraji.sanags.core.domain.states.CustomTextFieldState
import com.faraji.sanags.core.presentation.util.UiEvent
import com.faraji.sanags.core.util.Constants
import com.faraji.sanags.core.util.Resource
import com.faraji.sanags.core.util.UiText
import com.faraji.sanags.feature_set_info.domain.use_case.SendUserDataUseCase
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SetInfoScreenViewModel @Inject constructor(
    private val useCase: SendUserDataUseCase
) : ViewModel() {

    private val _firstNameFieldState = mutableStateOf(CustomTextFieldState())
    val firstNameFieldState: State<CustomTextFieldState> = _firstNameFieldState

    private val _lastNameFieldState = mutableStateOf(CustomTextFieldState())
    val lastNameFieldState: State<CustomTextFieldState> = _lastNameFieldState

    private val _mobileFieldState = mutableStateOf(CustomTextFieldState())
    val mobileFieldState: State<CustomTextFieldState> = _mobileFieldState

    private val _phoneFieldState = mutableStateOf(CustomTextFieldState())
    val phoneFieldState: State<CustomTextFieldState> = _phoneFieldState

    private val _addressFieldState = mutableStateOf(CustomTextFieldState())
    val addressFieldState: State<CustomTextFieldState> = _addressFieldState

    private val _state = mutableStateOf(SetInfoScreenState())
    val state: State<SetInfoScreenState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    fun onEvent(event: SetInfoScreenEvent) {
        when (event) {
            is SetInfoScreenEvent.EnteredAddress -> {
                _addressFieldState.value = addressFieldState.value.copy(
                    text = event.address
                )
            }
            is SetInfoScreenEvent.EnteredFirstName -> {
                _firstNameFieldState.value = firstNameFieldState.value.copy(
                    text = event.firstName
                )
            }
            is SetInfoScreenEvent.EnteredLastName -> {
                _lastNameFieldState.value = lastNameFieldState.value.copy(
                    text = event.lastName
                )
            }
            is SetInfoScreenEvent.EnteredMobile -> {
                _mobileFieldState.value = mobileFieldState.value.copy(
                    text = event.mobile
                )
            }
            is SetInfoScreenEvent.EnteredPhone -> {
                _phoneFieldState.value = phoneFieldState.value.copy(
                    text = event.phone
                )
            }
            is SetInfoScreenEvent.SelectedGender -> {
                _state.value = state.value.copy(
                    selectedGender = event.gender
                )
            }
            SetInfoScreenEvent.NextStepClicked -> {
                if (_state.value.latLng != null) {
                    _state.value = state.value.copy(
                        showMap = true
                    )
                }
            }
            SetInfoScreenEvent.OnSendDataClicked -> {
                _state.value = state.value.copy(
                    isLoading = true
                )
                _addressFieldState.value = addressFieldState.value.copy(
                    error = null
                )
                _firstNameFieldState.value = firstNameFieldState.value.copy(
                    error = null
                )
                _lastNameFieldState.value = lastNameFieldState.value.copy(
                    error = null
                )
                _phoneFieldState.value = phoneFieldState.value.copy(
                    error = null
                )
                _mobileFieldState.value = mobileFieldState.value.copy(
                    error = null
                )
                viewModelScope.launch {
                    val request = useCase(
                        firstName = _firstNameFieldState.value.text,
                        lastName = _lastNameFieldState.value.text,
                        mobile = _mobileFieldState.value.text,
                        phone = _phoneFieldState.value.text,
                        address = _addressFieldState.value.text,
                        latLng = _state.value.latLng,
                        gender = _state.value.selectedGender
                    )

                    _state.value = state.value.copy(
                        isLoading = false
                    )

                    if (request.addressError != null) {
                        _addressFieldState.value = addressFieldState.value.copy(
                            error = request.addressError
                        )
                    }
                    if (request.firstNameError != null) {
                        _firstNameFieldState.value = firstNameFieldState.value.copy(
                            error = request.firstNameError
                        )
                    }
                    if (request.lastNameError != null) {
                        _lastNameFieldState.value = lastNameFieldState.value.copy(
                            error = request.lastNameError
                        )
                    }
                    if (request.mobileError != null) {
                        _mobileFieldState.value = mobileFieldState.value.copy(
                            error = request.mobileError
                        )
                    }
                    if (request.phoneError != null) {
                        _phoneFieldState.value = phoneFieldState.value.copy(
                            error = request.phoneError
                        )
                    }

                    when (request.result) {
                        is Resource.Success -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    UiText.StringResource(
                                        R.string.dataSentSuccessfully
                                    )
                                )
                            )
                        }
                        is Resource.Error -> {
                            _eventFlow.emit(
                                UiEvent.ShowSnackbar(
                                    request.result.uiText ?: UiText.unknownError()
                                )
                            )
                        }
                    }
                }

            }
            is SetInfoScreenEvent.EnteredLatLng -> {
                Timber.e("${event.latLng}")
                _state.value = state.value.copy(
                    showMap = false,
                    latLng = event.latLng
                )
            }
        }
    }


}