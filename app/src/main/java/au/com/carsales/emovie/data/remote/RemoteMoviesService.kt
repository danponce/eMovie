package au.com.carsales.emovie.data.remote

import au.com.carsales.emovie.data.remote.RestConstants.GET_MOVIE_DETAIL
import au.com.carsales.emovie.data.remote.RestConstants.GET_MOVIE_VIDEOS
import au.com.carsales.emovie.data.remote.RestConstants.GET_TOP_RATED
import au.com.carsales.emovie.data.remote.RestConstants.GET_UPCOMING
import au.com.carsales.emovie.data.remote.RestConstants.PATH_MOVIE_ID
import au.com.carsales.emovie.data.remote.model.MovieData
import au.com.carsales.emovie.data.remote.model.MovieDetailData
import au.com.carsales.emovie.data.remote.model.MovieVideosResultData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
interface RemoteMoviesService {

    @GET(GET_UPCOMING)
    suspend fun getUpcomingMovies() : Response<MovieData?>

    @GET(GET_TOP_RATED)
    suspend fun getTopRated() : Response<MovieData?>

    @GET(GET_MOVIE_DETAIL)
    suspend fun getMovieById(
        @Path(PATH_MOVIE_ID) movieId: String = "",
    ): Response<MovieDetailData>

    @GET(GET_MOVIE_VIDEOS)
    suspend fun getMovieVideosById(
        @Path(PATH_MOVIE_ID) movieId: String = "",
    ): Response<MovieVideosResultData>

}