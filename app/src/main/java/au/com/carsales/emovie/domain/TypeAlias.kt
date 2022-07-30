package au.com.carsales.emovie.domain

import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.domain.model.DomainMovieDetail
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.ui.model.UIMovieItem


typealias DomainMovieDataState = APIState<List<DomainMovieItem>>
typealias DomainMovieDetailDataState = APIState<DomainMovieDetail>

typealias UiMovieDataState = APIState<List<UIMovieItem>>
