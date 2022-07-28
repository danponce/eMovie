package au.com.carsales.emovie.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.carsales.emovie.domain.usecase.GetUpcomingMoviesUseCase
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.State
import au.com.carsales.emovie.utils.base.viewmodel.BaseBindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestMoviesUseCase: GetUpcomingMoviesUseCase
) : BaseBindingViewModel() {

    private val _moviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val moviesLiveData: LiveData<List<UIMovieItem>> = _moviesLiveData

    val tvShowsStateLiveData = MutableLiveData<State<Unit>>()

    var lastQuery : String?= null

    fun getMovies() {
        viewModelScope.launch {
            getLatestMoviesUseCase.getUpcomingMovies().collect { it ->
                if(it!= null) {

                }
            }
        }
    }

}