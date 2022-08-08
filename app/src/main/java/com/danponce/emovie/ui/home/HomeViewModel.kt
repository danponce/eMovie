package com.danponce.emovie.ui.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.danponce.emovie.domain.usecase.GetTopRatedMoviesUseCase
import com.danponce.emovie.domain.usecase.GetUpcomingMoviesUseCase
import com.danponce.emovie.ui.mapper.UIMovieItemListMapper
import com.danponce.emovie.ui.model.UIMovieItem
import com.danponce.emovie.utils.base.coroutines.CoroutinesContextProvider
import com.danponce.emovie.utils.base.viewmodel.BaseViewModel
import com.danponce.emovie.utils.datastore.UserPreferences
import com.danponce.emovie.utils.datastore.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getLatestMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val movieItemMapper: UIMovieItemListMapper,
    private val userPreferencesRepository: UserPreferencesRepository,
    private val coroutinesContextProvider: CoroutinesContextProvider
) : BaseViewModel() {

    override fun getCoroutinesCtxProvider(): CoroutinesContextProvider {
        return coroutinesContextProvider
    }

    val isRecommendedMoviesEmpty = ObservableBoolean(false)

    lateinit var allLanguagesString: String
    lateinit var allReleaseYearsString: String

    // Keep the user preferences as a stream of changes
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow

    private val _userPreferencesLiveData = MutableLiveData<UserPreferences>()
    val userPreferencesLiveData: LiveData<UserPreferences> = _userPreferencesLiveData

    private val _upcomingMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val upcomingMoviesLiveData: LiveData<List<UIMovieItem>> = _upcomingMoviesLiveData

    private val _topRatedMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val topRatedMoviesLiveData: LiveData<List<UIMovieItem>> = _topRatedMoviesLiveData

    private val _recommendedMoviesLiveData = MutableLiveData<List<UIMovieItem>>()
    val recommendedMoviesLiveData: LiveData<List<UIMovieItem>> = _recommendedMoviesLiveData

    /**
     * Used to set the strings that
     * will be used later as the
     * option to "select all"
     *
     * @param allLanguages      the all languages string option
     * @param allYears          the all years string option
     */
    fun initFilterInfo(allLanguages: String, allYears: String) {
        allLanguagesString = allLanguages
        allReleaseYearsString = allYears
    }

    /**
     * Collects data within this view model
     * scope related to upcoming movies
     * updating the live data object
     */
    fun getUserPreferences() {
        viewModelScope.launch(Dispatchers.IO) {

            userPreferencesFlow
                .distinctUntilChanged()
                .collect {
                    _userPreferencesLiveData.postValue(it)
                }
        }
    }

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
            liveDatas = arrayOf(_topRatedMoviesLiveData, _recommendedMoviesLiveData),
            mapper = movieItemMapper
        )

    fun hasData(): Boolean {
        return (upcomingMoviesLiveData.value != null && topRatedMoviesLiveData.value != null)
    }

    fun getLastTopRatedData() = topRatedMoviesLiveData.value.orEmpty()
    fun getLastUpcomingData() = upcomingMoviesLiveData.value.orEmpty()

    fun getRecommendedMoviesLanguages(): List<String> {
        val languagesString = mutableListOf<String>()
        languagesString.add(allLanguagesString)
        languagesString.addAll(recommendedMoviesLiveData.value?.map { movie ->
            movie.displayLanguage
        }?.distinct()?.sorted().orEmpty())

        return languagesString
    }

    fun filterRecommendedMoviesByLanguage(language: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateLanguageFilter(language)
        }
    }

    fun getRecommendedMoviesYears(): List<String> {
        val yearsString = mutableListOf<String>()
        yearsString.add(allReleaseYearsString)
        yearsString.addAll(recommendedMoviesLiveData.value?.map { movie ->
            movie.releaseYear
        }?.distinct()?.sorted().orEmpty())

        return yearsString
    }

    fun filterRecommendedMoviesByYear(releaseYear: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateReleaseYearFilter(releaseYear)
        }
    }

    fun getFilteredRecommendedList(userPreferences: UserPreferences?): List<UIMovieItem> {
        val list = when (userPreferences) {
            null -> recommendedMoviesLiveData.value.orEmpty()
            else -> {
                recommendedMoviesLiveData.value?.filter { movie ->
                    var valid = true

                    val language = userPreferences.languageFilter
                    val year = userPreferences.releaseYearFilter

                    if (language.isNotEmpty()) {
                        valid = (language == movie.displayLanguage)

                        if (!valid) {
                            return@filter valid
                        }
                    }

                    if (year.isNotEmpty()) {
                        valid = (year == movie.releaseYear)
                    }

                    valid
                }.orEmpty()
            }
        }

        // Update value if is empty or not
        isRecommendedMoviesEmpty.set(list.isEmpty())

        return list
    }

}
