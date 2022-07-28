package au.com.carsales.emovie.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.State
import au.com.carsales.emovie.utils.base.viewmodel.BaseBindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor() : BaseBindingViewModel() {

    private val _favoritesLiveData = MutableLiveData<State<List<UIMovieItem>?>>()
    val favoritesLiveData : LiveData<State<List<UIMovieItem>?>> = _favoritesLiveData

//    private suspend fun getFavoritesDBRequest() = FavoriteTVShowsManager.getMo()

    fun getFavoritesTVShows() {

//        viewModelScope.launch(Dispatchers.IO) {
//            processAsyncJob(
//                block = { getFavoritesDBRequest() },
//                result = {
//                    val result = it?.filterNotNull()
//
//                    if(result.isNullOrEmpty()) {
//                        _favoritesLiveData.postValue(State.error())
//                        setErrorStatus()
//                    } else {
//                        _favoritesLiveData.postValue(State.success(result))
//                        setSuccessStatus()
//                    }
//                },
//                onError = {
//                    _favoritesLiveData.postValue(State.error())
//                    setErrorStatus()
//                }
//            )
//        }
    }

    fun deleteFromFavorites(item: UIMovieItem) {
        viewModelScope.launch(Dispatchers.IO) {
//            FavoriteTVShowsManager.deleteItem(item)

            getFavoritesTVShows()
        }
    }
}