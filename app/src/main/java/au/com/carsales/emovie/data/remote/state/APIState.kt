package au.com.carsales.emovie.data.remote.state

/**
 * Created by Dan on 27, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
sealed class APIState<out T> {
    data class Success<out T>(val data: T) : APIState<T>()
    data class Empty(val error: String) : APIState<Nothing>()
    data class Error(val error: String) : APIState<Nothing>()
}
