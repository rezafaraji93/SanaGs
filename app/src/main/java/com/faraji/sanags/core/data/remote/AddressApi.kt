package com.faraji.sanags.core.data.remote

import com.faraji.sanags.core.data.remote.requests.AddAddressRequest
import com.faraji.sanags.core.data.remote.responses.AddAddressResponse
import com.faraji.sanags.core.data.remote.responses.GetAddressesResponse
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AddressApi {

    @POST("karfarmas/address")
    suspend fun addAddress(@Body request: AddAddressRequest): AddAddressResponse

    @GET("karfarmas/address")
    suspend fun getAddresses(): List<GetAddressesResponse>

    companion object {
        const val BASE_URL = "https://stage.achareh.ir/api/"
    }

}