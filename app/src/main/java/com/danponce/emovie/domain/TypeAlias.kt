package com.danponce.emovie.domain

import com.danponce.emovie.data.remote.state.APIState
import com.danponce.emovie.domain.model.DomainMovieDetail
import com.danponce.emovie.domain.model.DomainMovieItem
import com.danponce.emovie.ui.model.UIMovieItem


typealias DomainMovieDataState = APIState<List<DomainMovieItem>>
typealias DomainMovieDetailDataState = APIState<DomainMovieDetail>

typealias UiMovieDataState = APIState<List<UIMovieItem>>
