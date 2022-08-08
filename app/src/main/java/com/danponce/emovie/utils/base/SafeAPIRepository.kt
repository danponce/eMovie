package com.danponce.emovie.utils.base

import retrofit2.Response

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
abstract class SafeAPIRepository {

    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>
    ): T? {
        return safeApiResult(call)

    }

    suspend fun <T : Any> safeApiListCall(
        call: suspend () -> Response<List<T?>?>
    ): List<T>? {
        return safeApiListResult(call)

    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): T? {
        try {
            val response = call.invoke()
            if (response.isSuccessful) return response.body()
            else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

    private suspend fun <T : Any> safeApiListResult(
        call: suspend () -> Response<List<T?>?>
    ): List<T>? {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return response.body() as List<T>?
            }
            else {
                throw Exception(response.message())
            }
        } catch (e: Exception) {
            throw e
        }
    }

}