package com.faraji.sanags.core.presentation.util

import com.faraji.sanags.core.util.UiText

sealed class UiEvent {
    data class ShowSnackbar(val uiText: UiText, val action: String? = null) : UiEvent()
    data class Navigate(val route: String) : UiEvent()
    object NavigateUp : UiEvent()
}