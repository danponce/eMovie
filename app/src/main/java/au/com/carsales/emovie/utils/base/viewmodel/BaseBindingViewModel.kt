package au.com.carsales.emovie.utils.base.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseBindingViewModel : ViewModel() {

    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var isError: ObservableBoolean = ObservableBoolean(false)
    var isEmpty: ObservableBoolean = ObservableBoolean(false)

    fun setLoadingStatus() {
        isLoading.set(true)
        isError.set(false)
        isEmpty.set(false)
    }

    fun setErrorStatus() {
        isLoading.set(false)
        isError.set(true)
        isEmpty.set(false)
    }

    fun setSuccessStatus() {
        isLoading.set(false)
        isError.set(false)
        isEmpty.set(false)
    }

    fun setEmptyStatus() {
        isLoading.set(false)
        isError.set(false)
        isEmpty.set(true)
    }
}