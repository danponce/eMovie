package com.danponce.emovie.data.remote

import com.danponce.emovie.data.remote.state.APIState
import com.danponce.emovie.domain.utils.Mapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */

fun <T, K, Y> apiFlow(
    call : suspend () -> Response<T>,
    validation : (T) -> Boolean = {true},
    mapper : Mapper<K, Y>,
    toMap : (T) -> K) : Flow<APIState<Y>> {

    return flow {
        val response = call.invoke()

        when {
            response.isSuccessful -> {
                response.body()?.let { data ->
                    if(!validation.invoke(data)) {
                        emit(APIState.Error("No movies available"))
                        return@let
                    }

                    val mappedObject = mapper.executeMapping(toMap.invoke(data))

                    mappedObject?.apply {
                        emit(APIState.Success(this))
                    }

                } ?: emit(APIState.Empty(response.message()))
            }

            else -> emit(APIState.Error(response.message()))
        }
    }.catch { emit(APIState.Error("API request error")) }.flowOn(Dispatchers.IO)
}