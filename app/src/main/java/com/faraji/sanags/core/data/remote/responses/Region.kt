package com.faraji.sanags.core.data.remote.responses

data class Region(
    val id: Int,
    val name: String,
    val city_object: CityObject,
    val state_object: StateObject
)