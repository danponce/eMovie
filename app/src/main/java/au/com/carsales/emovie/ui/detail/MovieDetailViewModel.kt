package au.com.carsales.emovie.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import au.com.carsales.emovie.ui.detail.episode.EpisodesSeason
import au.com.carsales.emovie.ui.model.UIMovieItem
import au.com.carsales.emovie.utils.base.State
import au.com.carsales.emovie.utils.base.viewmodel.BaseBindingViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
): BaseBindingViewModel() {

    val movieItemLiveData = MutableLiveData<UIMovieItem>()
    var showId : Int ?= null
    var movieItem : UIMovieItem?= null

    private val _isFavoriteLiveData = MutableLiveData<Boolean>()
    val isFavoriteLiveData : LiveData<Boolean> = _isFavoriteLiveData

//    private val _episodesLiveData = MutableLiveData<State<List<EpisodeViewData>>>()
//    val episodesLiveData : LiveData<State<List<EpisodeViewData>>> = _episodesLiveData
//
//    val episodeClickedLiveData = MutableLiveData<EpisodeViewData>()
//
//    var episodeList : List<EpisodeViewData>?= null
//
//    fun setClickedEpisode(episode : EpisodeViewData) {
//        episodeClickedLiveData.postValue(episode)
//    }

//    private suspend fun getEpisodesRequest () = withContext(Dispatchers.IO) {
//        getEpisodesUseCase.getEpisodes(showId ?: 0)
//    }

    fun getEpisodes() {
        viewModelScope.launch {
            setLoadingStatus()

//            processAsyncJob(
//                block = { getEpisodesRequest() },
//                result = {
//                    if(it.isNullOrEmpty()) {
//                        _episodesLiveData.postValue(State.error())
//
//                        setErrorStatus()
//                    } else {
////                        episodeList = it.mapNotNull { item -> episodesMapper.executeMapping(item) }
////
////                        _episodesLiveData.postValue(State.success(episodeList ?: listOf()))
//
//                        setSuccessStatus()
//                    }
//
//                },
//                onError = {
//                    _episodesLiveData.postValue(State.error())
//
//                    setErrorStatus()
//                }
//            )
        }
    }

    fun setMovie(data : UIMovieItem) {
        movieItemLiveData.postValue(data)
//        showId = data.id
        movieItem = data
    }

    fun getLastShow() = movieItem

//    fun getEpisodesSeasons() : List<EpisodesSeason> {
//
//        val episodesMap = hashMapOf<Int, ArrayList<EpisodeViewData>>()
//
//        episodeList?.forEach { episode ->
//            if(episodesMap.contains(episode.season)) {
//                episodesMap[episode.season]?.add(episode)
//            } else {
//                episodesMap[episode.season ?: 0] = arrayListOf(episode)
//            }
//        }
//
//        val seasonList = arrayListOf<EpisodesSeason>()
//
//        episodesMap.entries.forEach { entry ->
//            seasonList.add(EpisodesSeason(entry.key, entry.value))
//        }
//
//        return seasonList
//    }
//
//    fun getExistentEpisodesData(): List<EpisodeViewData>? = episodeList

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