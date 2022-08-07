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

    lateinit var allLanguagesString : String
    lateinit var allReleaseYearsString : String

    // Keep the user preferences as a stream of changes
    private val userPreferencesFlow = userPreferencesRepository.userPreferencesFlow
    val userPreferencesFlowLiveData = userPreferencesRepository.userPreferencesFlow.asLiveData()

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
    fun initFilterInfo(allLanguages : String, allYears: String) {
        allLanguagesString = allLanguages
        allReleaseYearsString = allYears
    }

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
            liveDatas = arrayOf(_topRatedMoviesLiveData, _recommendedMoviesLiveData),
            mapper = movieItemMapper
        )

    fun hasData(): Boolean {
        return (upcomingMoviesLiveData.value != null && topRatedMoviesLiveData.value != null)
    }

    fun getLastTopRatedData() = topRatedMoviesLiveData.value.orEmpty()
    fun getLastUpcomingData() = upcomingMoviesLiveData.value.orEmpty()

    fun getRecommendedMoviesLanguages() : List<String> {
        val languagesString = mutableListOf<String>()
        languagesString.add(allLanguagesString)
        languagesString.addAll(recommendedMoviesLiveData.value?.map { movie ->
            movie.displayLanguage
        }?.distinct()?.sorted().orEmpty())

        return languagesString
    }

    fun filterRecommendedMoviesByLanguage(language : String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateLanguageFilter(language)
        }
    }

    fun getRecommendedMoviesYears() : List<String> {
        val yearsString = mutableListOf<String>()
        yearsString.add(allReleaseYearsString)
        yearsString.addAll(recommendedMoviesLiveData.value?.map { movie ->
            movie.releaseYear
        }?.distinct()?.sorted().orEmpty())

        return yearsString
    }

    fun filterRecommendedMoviesByYear(releaseYear : String) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateReleaseYearFilter(releaseYear)
        }
    }

    fun getFilteredRecommendedList(userPreferences: UserPreferences?): List<UIMovieItem> {
        return when(userPreferences) {
            null -> recommendedMoviesLiveData.value.orEmpty()
            else -> {
                recommendedMoviesLiveData.value?.filter { movie ->
                    var valid = true

                    val language = userPreferences.languageFilter
                    val year = userPreferences.releaseYearFilter

                    if(language.isNotEmpty()) {
                        valid = (language == movie.displayLanguage)

                        if(!valid) {
                            return@filter valid
                        }
                    }

                    if(year.isNotEmpty()) {
                        valid = (year == movie.releaseYear)
                    }

                    valid
                }.orEmpty()
            }
        }
    }

}