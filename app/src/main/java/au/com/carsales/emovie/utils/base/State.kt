package au.com.carsales.emovie.utils.base

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
sealed class State<T> {

    class Loading<T> : State<T>()
    class Empty<T> : State<T>()
    data class Error<T>(val errorMessage : String?) : State<T>()
    data class Success<T>(val data : T) : State<T>()

    companion object {
        fun <T> loading() : State<T> = Loading()
        fun <T> error(errorMessage: String? = null) : State<T> = Error(errorMessage)
        fun <T> success(data: T) : State<T> = Success(data)
        fun <T> empty() : State<T> = Empty()
    }
}
