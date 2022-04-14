package com.faraji.sanags.feature_get_info.presentation

import com.faraji.sanags.core.domain.model.AddressList

data class GetInfoScreenState(
    val isLoading: Boolean = true,
    val addressList: List<AddressList> = emptyList()
)
