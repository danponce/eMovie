package au.com.carsales.emovie.data.remote

import au.com.carsales.emovie.data.remote.RestConstants.GET_UPCOMING
import au.com.carsales.emovie.data.remote.model.MovieData
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface RemoteMoviesService {

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMovies() : Response<MovieData?>

}