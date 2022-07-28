package au.com.carsales.emovie.domain

import au.com.carsales.emovie.data.remote.state.APIState
import au.com.carsales.emovie.domain.model.DomainMovieItem
import au.com.carsales.emovie.ui.model.UIMovieItem


typealias DomainMovieDataState = APIState<List<DomainMovieItem>>

typealias UiMovieDataState = APIState<List<UIMovieItem>>
