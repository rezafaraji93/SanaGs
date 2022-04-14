package com.faraji.sanags.core.data.repository

import com.faraji.sanags.core.data.remote.AddressApi
import com.faraji.sanags.core.data.remote.requests.AddAddressRequest
import com.faraji.sanags.core.data.remote.responses.AddAddressResponse
import com.faraji.sanags.core.domain.model.AddressList
import com.faraji.sanags.core.domain.repository.AddressRepository
import com.faraji.sanags.core.util.Resource
import com.faraji.sanags.core.util.UiText
import retrofit2.HttpException
import timber.log.Timber

class AddressRepositoryImpl(
    private val api: AddressApi
) : AddressRepository {
    override suspend fun addAddress(request: AddAddressRequest): Resource<AddAddressResponse> {
        return try {
            val response = api.addAddress(request)
            Resource.Success(response)
        } catch (e: HttpException) {
            Timber.e(e)
            Resource.Error(
                UiText.connectionError()
            )
        } catch (e: Exception) {
            Timber.e(e)
            Resource.Error(
                UiText.unknownError()
            )
        }
    }

    override suspend fun getAddresses(): Resource<List<AddressList>> {
        return try {
            val response = api.getAddresses().map { it.toAddressList() }
            Resource.Success(response)

        } catch (e: HttpException) {
            Timber.e(e)
            Resource.Error(
                UiText.connectionError()
            )
        } catch (e: Exception) {
            Timber.e(e)
            Resource.Error(
                UiText.unknownError()
            )
        }
    }
}