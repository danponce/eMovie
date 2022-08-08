package au.com.carsales.emovie.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.carsales.emovie.domain.usecase.DeleteFavoriteMovieUseCase
import au.com.carsales.emovie.domain.usecase.GetFavoriteMoviesUseCase
import au.com.carsales.emovie.ui.mapper.UIMovieItemListMapper
import au.com.carsales.emovie.ui.mapper.UIMovieItemMapper
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.coroutines.CoroutinesContextProvider
import au.com.carsales.emovie.utils.base.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase,
    private val deleteFavoriteMovieUseCase: DeleteFavoriteMovieUseCase,
    private val movieListMapper: UIMovieItemListMapper,
    private val movieItemMapper: UIMovieItemMapper,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel() {

    override fun getCoroutinesCtxProvider(): CoroutinesContextProvider {
        return coroutinesContextProvider
    }

    private val _favoritesLiveData = MutableLiveData<List<UIMovieItem>?>()
    val favoritesLiveData : LiveData<List<UIMovieItem>?> = _favoritesLiveData

    fun getFavoritesMovies() {
        useCaseCollect(
            flowCall = { getFavoriteMoviesUseCase.getFavoriteMovies() },
            liveData = _favoritesLiveData,
            mapper = movieListMapper
        )
    }

    fun deleteFromFavorites(item: UIMovieItem) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteMovieUseCase.deleteFavoriteMovie(movieItemMapper.executeMapping(item))
        }
    }
}