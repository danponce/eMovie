package au.com.carsales.emovie.ui.detail.episode

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
class EpisodeDetailViewModel @Inject constructor() : ViewModel() {

//    var episode : EpisodeViewData?= null

    fun setName(exampleName: String) {
        _secondScreenNameState.value = exampleName
    }

    // To manage the loading state of view with compose
    private val _secondScreenNameState = MutableStateFlow("")
    val secondScreenNameState: StateFlow<String> = _secondScreenNameState.asStateFlow()

}