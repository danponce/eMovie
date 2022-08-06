package au.com.carsales.emovie.ui.home

import androidx.lifecycle.*
import au.com.carsales.emovie.domain.usecase.GetTopRatedMoviesUseCase
import au.com.carsales.emovie.domain.usecase.GetUpcomingMoviesUseCase
import au.com.carsales.emovie.ui.mapper.UIMovieItemListMapper
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.viewmodel.BaseViewModel
import au.com.carsales.emovie.utils.datastore.UserPreferences
import au.com.carsales.emovie.utils.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    // Keep the user preferences as a stream of changes
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow
    val userPreferencesFlowLiveData = userPreferencesRepository.userPreferencesFlow.asLiveData()

    private val _userPreferencesLiveData = MutableLiveData<UserPreferences>()
    val userPreferencesLiveData: LiveData<UserPreferences> = _userPreferencesLiveData

    private val _upcomingMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val upcomingMoviesLiveData: LiveData<List<UIMovieItem>> = _upcomingMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val topRatedMoviesLiveData: LiveData<List<UIMovieItem>> = _topRatedMoviesLiveData

    /**
     * Collects data within this view model
     * scope related to upcoming movies
     * updating the live data object
     */
    fun getUserPreferences() =
        useCaseCollect (
            flowCall =  { userPreferencesFlow },
            liveData = _userPreferencesLiveData
        )

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

    fun hasData(): Boolean {
        return (upcomingMoviesLiveData.value != null && topRatedMoviesLiveData.value != null)
    }

    fun getLastTopRatedData() = topRatedMoviesLiveData.value.orEmpty()
    fun getLastUpcomingData() = upcomingMoviesLiveData.value.orEmpty()

    fun getRecommendedMoviesLanguages() : List<String> {
        return topRatedMoviesLiveData.value?.map { movie ->
            movie.displayLanguage
        }?.distinct().orEmpty()
    }

    fun filterRecommendedMoviesByLanguage(language : String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateLanguageFilter(language)
        }
    }

    fun getRecommendedMoviesYears() : List<String> {
        return topRatedMoviesLiveData.value?.map { movie ->
            movie.releaseYear
        }?.distinct().orEmpty()
    }

    fun filterRecommendedMoviesByYear(releaseYear : String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateReleaseYearFilter(releaseYear)
        }
    }

    fun getFilteredRecommendedList(userPreferences: UserPreferences?): List<UIMovieItem> {
        return when(userPreferences) {
            null -> topRatedMoviesLiveData.value.orEmpty()
            else -> {
                topRatedMoviesLiveData.value?.filter { movie ->
                    when {
                        userPreferences.languageFilter.isNotEmpty() &&
                                userPreferences.releaseYearFilter.isNotEmpty()-> {
                            (userPreferences.languageFilter == movie.displayLanguage && userPreferences.releaseYearFilter == movie.releaseYear)
                                }

                        userPreferences.languageFilter.isNotEmpty() -> {
                            userPreferences.languageFilter == movie.displayLanguage
                        }

                        userPreferences.releaseYearFilter.isNotEmpty() -> {
                            userPreferences.releaseYearFilter == movie.releaseYear
                        }

                        else -> true

                    }
                }.orEmpty()
            }
        }
    }

}