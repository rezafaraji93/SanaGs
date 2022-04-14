package com.faraji.sanags.core.data.remote.responses

data class RegionX(
    val id: Int,
    val name: String,
    val city_object: CityObjectX,
    val state_object: StateObjectX
)