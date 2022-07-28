package au.com.carsales.emovie.data.remote.interceptor

import au.com.carsales.emovie.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created by Dan on 27, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class SecurityInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val url: HttpUrl = request.url
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .addQueryParameter("language", "en-US")
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}