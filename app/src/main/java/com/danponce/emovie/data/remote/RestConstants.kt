package com.danponce.emovie.data.remote

/**
 * Created by Dan on 24, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
object RestConstants {

    // GET VALUES
    const val GET_UPCOMING = "movie/upcoming"
    const val GET_TOP_RATED = "movie/top_rated"
    const val GET_MOVIE_DETAIL = "movie/{movie_id}"
    const val GET_MOVIE_VIDEOS = "movie/{movie_id}/videos"
    const val GET_SIMILAR_MOVIES = "movie/{movie_id}/similar"

    // PATH VALUES
    const val PATH_MOVIE_ID = "movie_id"
}