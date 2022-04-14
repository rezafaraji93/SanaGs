package com.faraji.sanags.feature_set_info.presentation

import com.faraji.sanags.feature_set_info.domain.model.Gender
import com.google.android.gms.maps.model.LatLng

sealed class SetInfoScreenEvent {
    data class EnteredFirstName(val firstName: String) : SetInfoScreenEvent()
    data class EnteredLastName(val lastName: String) : SetInfoScreenEvent()
    data class EnteredMobile(val mobile: String) : SetInfoScreenEvent()
    data class EnteredPhone(val phone: String) : SetInfoScreenEvent()
    data class EnteredAddress(val address: String) : SetInfoScreenEvent()
    data class SelectedGender(val gender: Gender) : SetInfoScreenEvent()
    data class EnteredLatLng(val latLng: LatLng) : SetInfoScreenEvent()
    object NextStepClicked : SetInfoScreenEvent()
    object OnSendDataClicked : SetInfoScreenEvent()
}
