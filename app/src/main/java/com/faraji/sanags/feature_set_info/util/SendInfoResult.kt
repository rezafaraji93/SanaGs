package com.faraji.sanags.feature_set_info.util

import com.faraji.sanags.core.util.Resource

data class SendInfoResult(
    val firstNameError: InfoError? = null,
    val lastNameError: InfoError? = null,
    val mobileError: InfoError? = null,
    val phoneError: InfoError? = null,
    val addressError: InfoError? = null,
    val result: Resource<Unit>? = null
)
