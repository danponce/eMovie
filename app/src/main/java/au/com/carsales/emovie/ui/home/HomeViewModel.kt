package au.com.carsales.emovie.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import au.com.carsales.emovie.domain.usecase.GetTopRatedMoviesUseCase
import au.com.carsales.emovie.domain.usecase.GetUpcomingMoviesUseCase
import au.com.carsales.emovie.ui.mapper.UIMovieItemListMapper
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.DateUtils
import au.com.carsales.emovie.utils.LanguageHelper
import au.com.carsales.emovie.utils.base.viewmodel.BaseViewModel
import au.com.carsales.emovie.utils.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieItemMapper: UIMovieItemListMapper,
    private val userPreferencesRepository: UserPreferencesRepository
) : BaseViewModel() {

    val initialUserPreferencesEvent = liveData {
        emit(userPreferencesRepository.fetchInitialPreferences())
    }
    val selectedLanguage : ObservableField<String> = ObservableField()

    private val _upcomingMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val upcomingMoviesLiveData: LiveData<List<UIMovieItem>> = _upcomingMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val topRatedMoviesLiveData: LiveData<List<UIMovieItem>> = _topRatedMoviesLiveData

    /**
     * Collects data within this view model
     * scope related to upcoming movies
     * updating the live data object
     */
    fun getUpcomingMovies() =
        useCaseCollect(
            flowCall = { getLatestMoviesUseCase.getUpcomingMovies() },
            liveData = _upcomingMoviesLiveData,
            mapper = movieItemMapper
        )

    /**
     * Collects data within this view model
     * scope related to top rated movies
     * updating the live data object
     */
    fun getTopRatedMovies() =
        useCaseCollect(
            flowCall = { getTopRatedMoviesUseCase.getTopRatedMovies() },
            liveData = _topRatedMoviesLiveData,
            mapper = movieItemMapper
        )

    fun setInitialFilterData() {

    }

    fun hasData(): Boolean {
        return (upcomingMoviesLiveData.value != null && topRatedMoviesLiveData.value != null)
    }

    fun getLastTopRatedData() = topRatedMoviesLiveData.value.orEmpty()
    fun getLastUpcomingData() = upcomingMoviesLiveData.value.orEmpty()

    fun getRecommendedMoviesLanguages() : List<String> {
        return topRatedMoviesLiveData.value?.map { movie ->

            // Set the display language so
            // we can filter them after
            movie.displayLanguage = LanguageHelper.getISOToLanguage(movie.originalLanguage)
            movie.displayLanguage.orEmpty()
        }?.distinct().orEmpty()
    }

    fun getRecommendedMoviesLanguagesFilteredByLanguage(language : String) : List<UIMovieItem> {
        return topRatedMoviesLiveData.value?.filter { movie -> movie.displayLanguage == language }.orEmpty()
    }

    fun getRecommendedMoviesYears() : List<String> {
        return topRatedMoviesLiveData.value?.map { movie ->

            // Set the release year so
            // we can filter them after
            movie.releaseYear = DateUtils.getYearFromDate(movie.releaseYear.orEmpty(), DateUtils.yyyyMMddFormat)
            movie.releaseYear.orEmpty()
        }?.distinct().orEmpty()
    }

    fun getRecommendedMoviesLanguagesFilteredByYear(releaseYear : String) : List<UIMovieItem> {
        return topRatedMoviesLiveData.value?.filter { movie -> movie.releaseYear == releaseYear }.orEmpty()
    }

}