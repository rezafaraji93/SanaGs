package com.faraji.sanags.core.data.remote.responses

import com.faraji.sanags.core.domain.model.AddressList

data class GetAddressesResponse(
    val id: Int,
    val address_id: String,
    val region: Region,
    val address: String,
    val last_name: String,
    val first_name: String,
    val gender: String,
    val lat: Double,
    val lng: Double,
    val coordinate_mobile: String,
    val coordinate_phone_number: String
) {
    fun toAddressList(): AddressList {
        return AddressList(
            address = address,
            name = "$first_name $last_name",
            mobile = coordinate_mobile
        )
    }
}