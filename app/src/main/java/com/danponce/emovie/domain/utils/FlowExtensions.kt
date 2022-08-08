package com.danponce.emovie.domain.utils

import com.danponce.emovie.data.remote.state.APIState
import com.danponce.emovie.utils.base.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * Created by Dan on 27, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
fun <T> resultFlow(
    databaseQuery: suspend () -> Flow<T?>,
    networkCall: suspend () -> Flow<APIState<T>>,
    saveCallResult: suspend (T) -> Unit
): Flow<State<T>> =
    flow {

        // Just to make sure that the network
        // call flow is executed just once
        var isNetworkExecuted = false

        combine(databaseQuery.invoke(), networkCall.invoke()) { db, network ->

            emit(State.loading<T>())

            if (db != null) {
                emit(State.success<T>(db))
            }

            if(!isNetworkExecuted) {
                when (network) {
                    is APIState.Success -> {
                        saveCallResult.invoke(network.data)
                    }

                    is APIState.Error -> {
                        if(db == null) {
                            emit(State.error<T>(errorMessage = network.error))
                        }
                    }

                    is APIState.Empty -> {
                        if(db == null) {
                            emit(State.empty<T>())
                        }
                    }
                }

                isNetworkExecuted = true
            }

        }.collect()

    }.flowOn(Dispatchers.IO)

/**
 * Executes a database query
 * returning a State object
 */
fun <T> databaseResultFlow(
    databaseQuery: suspend () -> Flow<T>
): Flow<State<T>> =
    flow<State<T>> {
        databaseQuery.invoke().collect { result ->
            when(result) {
                null -> emit(State.empty())
                else -> emit(State.success(result))
            }
        }

    }.flowOn(Dispatchers.IO)

