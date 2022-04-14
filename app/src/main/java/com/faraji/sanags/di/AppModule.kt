package com.faraji.sanags.di

import com.faraji.sanags.core.data.remote.AddressApi
import com.faraji.sanags.core.data.repository.AddressRepositoryImpl
import com.faraji.sanags.core.domain.repository.AddressRepository
import com.faraji.sanags.core.util.BasicAuthInterceptor
import com.faraji.sanags.core.util.Constants
import com.faraji.sanags.feature_set_info.domain.use_case.SendUserDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import javax.net.ssl.HostnameVerifier

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }
            .addInterceptor(
                BasicAuthInterceptor(
                    Constants.BASIC_AUTH_USERNAME,
                    Constants.BASIC_AUTH_PASSWORD
                )
            ).build()
    }

    @Provides
    @Singleton
    fun provideAddressApi(client: OkHttpClient): AddressApi {
        return Retrofit.Builder()
            .baseUrl(AddressApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AddressApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: AddressApi): AddressRepository {
        return AddressRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: AddressRepository): SendUserDataUseCase {
        return SendUserDataUseCase(repository)
    }


}