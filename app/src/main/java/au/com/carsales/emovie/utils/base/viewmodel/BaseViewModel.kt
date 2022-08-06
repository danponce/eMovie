package au.com.carsales.emovie.utils.base.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import au.com.carsales.emovie.domain.utils.Mapper
import au.com.carsales.emovie.utils.base.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
abstract class BaseViewModel : ViewModel() {

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

    /**
     * Executes a use case flow call using
     * corresponding mapper and updating
     * given live data object
     *
     * @param flowCall      the flow call of the use case
     * @param liveData      the live data object to update with values
     * @param mapper        the related mapper
     */
    fun <T, K> useCaseCollect(
        flowCall : suspend () -> Flow<State<T>>,
        liveData : MutableLiveData<K>,
        mapper : Mapper<T, K>
    ) {
        viewModelScope.launch(Dispatchers.IO) {

            setLoadingStatus()

            flowCall()
                .distinctUntilChanged()
                .catch { setErrorStatus() }
                .collect { state ->

                    when (state) {
                        is State.Success -> {
                            liveData.postValue(mapper.executeMapping(state.data))
                            setSuccessStatus()
                        }

                        is State.Empty -> {
                            setEmptyStatus()
                        }

                        is State.Error -> {
                            setErrorStatus()
                        }
                    }
                }
        }
    }
}