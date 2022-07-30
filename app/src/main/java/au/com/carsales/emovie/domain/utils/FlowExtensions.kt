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

        // Just to make sure that the network
        // call flow is executed just once
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

