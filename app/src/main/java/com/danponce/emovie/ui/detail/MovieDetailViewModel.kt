package com.danponce.emovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.danponce.emovie.domain.usecase.AddFavoriteMovieUseCase
import com.danponce.emovie.domain.usecase.DeleteFavoriteMovieUseCase
import com.danponce.emovie.domain.usecase.GetIsFavoriteMovieUseCase
import com.danponce.emovie.domain.usecase.GetMovieDetailUseCase
import com.danponce.emovie.ui.mapper.UIMovieDetailMapper
import com.danponce.emovie.ui.mapper.UIMovieItemMapper
import com.danponce.emovie.ui.model.UIMovieDetail
import com.danponce.emovie.ui.model.UIMovieItem
import com.danponce.emovie.utils.base.coroutines.CoroutinesContextProvider
import com.danponce.emovie.utils.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    private val isFavoriteMovieUseCase: GetIsFavoriteMovieUseCase,
    private val addFavoriteMovieUseCase: AddFavoriteMovieUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val movieDetailMapper: UIMovieDetailMapper,
    private val movieItemMapper: UIMovieItemMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider
): BaseViewModel() {

    override fun getCoroutinesCtxProvider(): CoroutinesContextProvider {
        return coroutinesContextProvider
    }

    val movieItemLiveData = MutableLiveData<UIMovieItem>()
    var movieItem : UIMovieItem?= null

    private val _isFavoriteLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData : LiveData<Boolean> = _isFavoriteLiveData

    private val _movieDetailsLiveData = MutableLiveData<UIMovieDetail>()
    val movieDetailsLiveData: LiveData<UIMovieDetail> = _movieDetailsLiveData

    fun setMovie(data : UIMovieItem) {
        movieItemLiveData.postValue(data)
        movieItem = data
    }

    fun getLastMovieDetail() = movieDetailsLiveData.value

    fun isShowFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            isFavoriteMovieUseCase.isMovieFavorite(id).collect {
                _isFavoriteLiveData.postValue(it)
            }
        }
    }

    fun addFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            addFavoriteMovieUseCase.addFavoriteMovie(movieItemMapper.executeMapping(movieItem))
        }
    }

    fun isActualShowFavorite() = _isFavoriteLiveData.value ?: false

    fun deleteFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteMovieUseCase.deleteFavoriteMovie(movieItemMapper.executeMapping(movieItem))
        }
    }

    fun getMovieDetails() {
        useCaseCollect(
            flowCall = { getMovieDetailUseCase.getMovieDetail(movieItem?.id.toString()) },
            liveData = _movieDetailsLiveData,
            mapper = movieDetailMapper
        )
    }

    fun hasData(): Boolean {
        return movieDetailsLiveData.value != null
    }
}