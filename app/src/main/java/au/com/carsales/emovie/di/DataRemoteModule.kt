package au.com.carsales.emovie.di

import au.com.carsales.emovie.BuildConfig
import au.com.carsales.emovie.data.remote.RemoteMoviesService
import au.com.carsales.emovie.data.remote.interceptor.SecurityInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@InstallIn(SingletonComponent::class)
@Module
object DataRemoteModule {

    @Singleton
    @Provides
    fun provideTLSConnection(): ConnectionSpec {
        return ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_2)
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        connectionSpec: ConnectionSpec
    ) : OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder = OkHttpClient.Builder()

        clientBuilder.connectionSpecs(Collections.singletonList(connectionSpec))
        clientBuilder.addInterceptor(SecurityInterceptor())

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) : Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.API_BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideAPI(retrofit: Retrofit) = retrofit.create(RemoteMoviesService::class.java)
}