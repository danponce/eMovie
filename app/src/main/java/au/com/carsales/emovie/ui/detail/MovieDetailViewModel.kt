package au.com.carsales.emovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
): BaseViewModel() {

    val movieItemLiveData = MutableLiveData<UIMovieItem>()
    var showId : Int ?= null
    var movieItem : UIMovieItem?= null

    private val _isFavoriteLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData : LiveData<Boolean> = _isFavoriteLiveData

    fun setMovie(data : UIMovieItem) {
        movieItemLiveData.postValue(data)
//        showId = data.id
        movieItem = data
    }

    fun getLastShow() = movieItem

    fun isShowFavorite() {
//        viewModelScope.launch(Dispatchers.IO) {
//            val result = async { FavoriteTVShowsManager.isFavorite(showId ?: 0) }
//            val isFavorite = result.await()
//
//            _isFavoriteLiveData.postValue(isFavorite)
//        }
    }

    fun isActualShowFavorite() = _isFavoriteLiveData.value ?: false

    fun deleteFavorite() {
//        showItem?.let {
//            viewModelScope.launch(Dispatchers.IO) {
//                FavoriteTVShowsManager.deleteItem(it)
//                _isFavoriteLiveData.postValue(false)
//            }
//        }
    }

    fun addFavorite() {
//        showItem?.let {
//            viewModelScope.launch(Dispatchers.IO) {
//                FavoriteTVShowsManager.insertItem(it)
//                _isFavoriteLiveData.postValue(true)
//            }
//        }
    }
}