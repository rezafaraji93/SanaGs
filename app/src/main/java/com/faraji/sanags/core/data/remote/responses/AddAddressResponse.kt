package com.faraji.sanags.core.data.remote.responses

data class AddAddressResponse(
    val id: Int,
    val address_id: String,
    val region: RegionX,
    val address: String,
    val last_name: String,
    val first_name: String,
    val gender: String,
    val lat: Double,
    val lng: Double,
    val coordinate_mobile: String,
    val coordinate_phone_number: String
)