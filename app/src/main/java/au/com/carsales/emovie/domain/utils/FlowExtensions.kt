package au.com.carsales.emovie.domain.utils

import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.utils.base.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Created by Dan on 27, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */

fun <T> resultFlow(
    databaseQuery: suspend () -> Flow<T>,
    networkCall: suspend () -> Flow<APIState<T>>,
    saveCallResult: suspend (T) -> Unit
): Flow<State<T>> =
    flow {

        var isNetworkExecuted = false

        combine(databaseQuery.invoke(), networkCall.invoke()) { db, network ->

            if (db != null) {
                emit(State.success<T>(db))
            } else {
                emit(State.empty<T>())
            }

            if(!isNetworkExecuted) {
                when (network) {
                    is APIState.Success -> {
                        saveCallResult.invoke(network.data as T)
                    }

                    is APIState.Error -> {
                        emit(State.error<T>(errorMessage = network.error))
                    }

                    is APIState.Empty -> {
                        emit(State.empty<T>())
                    }
                }

                isNetworkExecuted = true
            }

        }.collect()
//        databaseQuery.invoke().collect {
//
//        }
//
//        val responseStatus = networkCall.invoke()
//
//        responseStatus.collect {
//            when(it) {
//                is APIState.Success -> {
//                    saveCallResult.invoke(it.data as T)
//                }
//
//                is APIState.Error -> {
//                    emit(State.error<T>(errorMessage = it.error))
//                }
//
//                is APIState.Empty -> {
//                    emit(State.empty<T>())
//                }
//            }
//        }
    }.flowOn(Dispatchers.IO)

