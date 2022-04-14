package com.faraji.sanags.feature_set_info.domain.use_case

import com.faraji.sanags.core.data.remote.requests.AddAddressRequest
import com.faraji.sanags.core.domain.repository.AddressRepository
import com.faraji.sanags.core.util.Resource
import com.faraji.sanags.feature_set_info.domain.model.Gender
import com.faraji.sanags.feature_set_info.util.InfoError
import com.faraji.sanags.feature_set_info.util.SendInfoResult
import com.google.android.gms.maps.model.LatLng

class SendUserDataUseCase(
    private val repository: AddressRepository
) {

    suspend operator fun invoke(
        firstName: String,
        lastName: String,
        mobile: String,
        phone: String,
        address: String,
        latLng: LatLng?,
        gender: Gender
    ): SendInfoResult {

        val firstNameError = when {
            firstName.length < 3 -> InfoError.InputTooShort
            firstName.isEmpty() -> InfoError.FieldEmpty
            else -> null
        }

        val lastNameError = when {
            lastName.length < 3 -> InfoError.InputTooShort
            lastName.isEmpty() -> InfoError.FieldEmpty
            else -> null
        }

        val mobileError = when {
            mobile.length < 3 -> InfoError.InputTooShort
            mobile.isEmpty() -> InfoError.FieldEmpty
            else -> null
        }

        val phoneError = when {
            phone.length < 3 -> InfoError.InputTooShort
            phone.isEmpty() -> InfoError.FieldEmpty
            else -> null
        }

        val addressError = when {
            address.length < 3 -> InfoError.InputTooShort
            address.isEmpty() -> InfoError.FieldEmpty
            else -> null
        }

        if (firstNameError != null || lastNameError != null || mobileError != null || phoneError != null || addressError != null) {
            return SendInfoResult(
                firstNameError = firstNameError,
                lastNameError = lastNameError,
                mobileError = mobileError,
                phoneError = phoneError,
                addressError = addressError
            )
        }

        val request = AddAddressRequest(
            first_name = firstName,
            last_name = lastName,
            coordinate_mobile = mobile,
            coordinate_phone_number = phone,
            address = address,
            lat = latLng?.longitude,
            lng = latLng?.longitude,
            gender = when (gender) {
                Gender.Female -> "Female"
                Gender.Male -> "Male"
            }
        )

        return when (val result = repository.addAddress(request)) {
            is Resource.Success -> SendInfoResult(
                result = Resource.Success(Unit)
            )
            is Resource.Error -> SendInfoResult(
                result = Resource.Error(result.uiText)
            )
        }

    }

}