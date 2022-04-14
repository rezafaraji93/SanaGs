package com.faraji.sanags.core.domain.states

data class CustomTextFieldState(
    var text: String = "",
    val error: Error? = null
)