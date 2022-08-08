package com.danponce.emovie.data.remote

import com.danponce.emovie.data.remote.RestConstants.GET_MOVIE_DETAIL
import com.danponce.emovie.data.remote.RestConstants.GET_MOVIE_VIDEOS
import com.danponce.emovie.data.remote.RestConstants.GET_TOP_RATED
import com.danponce.emovie.data.remote.RestConstants.GET_UPCOMING
import com.danponce.emovie.data.remote.RestConstants.PATH_MOVIE_ID
import com.danponce.emovie.data.remote.model.MovieData
import com.danponce.emovie.data.remote.model.MovieDetailData
import com.danponce.emovie.data.remote.model.MovieVideosResultData
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