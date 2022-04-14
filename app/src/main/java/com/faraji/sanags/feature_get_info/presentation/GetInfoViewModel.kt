package com.faraji.sanags.feature_get_info.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faraji.sanags.core.domain.repository.AddressRepository
import com.faraji.sanags.core.presentation.util.Screen
import com.faraji.sanags.core.presentation.util.UiEvent
import com.faraji.sanags.core.util.Resource
import com.faraji.sanags.core.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetInfoViewModel @Inject constructor(
    private val repository: AddressRepository
) : ViewModel() {

    private val _state = mutableStateOf(GetInfoScreenState())
    val state: State<GetInfoScreenState> = _state

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init {
        getAddressList()
    }

    fun onAddAddressClicked() {
        viewModelScope.launch {
            _eventFlow.emit(
                UiEvent.Navigate(
                    Screen.SetInfoScreen.route + "?lat=&lng="
                )
            )
        }
    }

    fun getAddressList() {
        _state.value = state.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            when (val result = repository.getAddresses()) {
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        addressList = result.data ?: emptyList()
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                    _eventFlow.emit(
                        UiEvent.ShowSnackbar(
                            result.uiText ?: UiText.unknownError()
                        )
                    )
                }
            }
        }
    }

}