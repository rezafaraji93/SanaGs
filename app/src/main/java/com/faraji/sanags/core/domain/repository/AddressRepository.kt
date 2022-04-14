package com.faraji.sanags.core.domain.repository

import com.faraji.sanags.core.data.remote.requests.AddAddressRequest
import com.faraji.sanags.core.data.remote.responses.AddAddressResponse
import com.faraji.sanags.core.data.remote.responses.GetAddressesResponse
import com.faraji.sanags.core.domain.model.AddressList
import com.faraji.sanags.core.util.Resource

interface AddressRepository {

    suspend fun addAddress(request: AddAddressRequest): Resource<AddAddressResponse>

    suspend fun getAddresses(): Resource<List<AddressList>>

}