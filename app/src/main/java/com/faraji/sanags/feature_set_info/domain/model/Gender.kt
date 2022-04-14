package com.faraji.sanags.feature_set_info.domain.model

sealed class Gender(val type: String) {
    object Male : Gender("آقا")
    object Female : Gender("خانم")
}
