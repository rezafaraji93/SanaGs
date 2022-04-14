package com.faraji.sanags.feature_set_info.presentation

import com.faraji.sanags.feature_set_info.domain.model.Gender
import com.google.android.gms.maps.model.LatLng

data class SetInfoScreenState(
    val isLoading: Boolean = false,
    val selectedGender: Gender = Gender.Male,
    val tabList: List<Gender> = listOf(
        Gender.Male,
        Gender.Female
    ),
    val latLng: LatLng? = null,
    val showMap: Boolean = false
)
