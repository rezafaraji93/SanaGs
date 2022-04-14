package com.faraji.sanags.feature_set_info.util

sealed class InfoError : Error() {
    object FieldEmpty : InfoError()
    object InputTooShort : InfoError()
}