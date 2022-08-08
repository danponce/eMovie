package com.danponce.emovie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danponce.emovie.data.local.DBConstants.MOVIE_DETAILS_TABLE
import com.danponce.emovie.data.remote.RemoteConstants.ADULT
import com.danponce.emovie.data.remote.RemoteConstants.BACKDROP_PATH
import com.danponce.emovie.data.remote.RemoteConstants.BUDGET
import com.danponce.emovie.data.remote.RemoteConstants.GENRES
import com.danponce.emovie.data.remote.RemoteConstants.HOMEPAGE
import com.danponce.emovie.data.remote.RemoteConstants.ID
import com.danponce.emovie.data.remote.RemoteConstants.IMDB_ID
import com.danponce.emovie.data.remote.RemoteConstants.ORIGINAL_LANGUAGE
import com.danponce.emovie.data.remote.RemoteConstants.ORIGINAL_TITLE
import com.danponce.emovie.data.remote.RemoteConstants.OVERVIEW
import com.danponce.emovie.data.remote.RemoteConstants.POPULARITY
import com.danponce.emovie.data.remote.RemoteConstants.POSTER_PATH
import com.danponce.emovie.data.remote.RemoteConstants.RELEASE_DATE
import com.danponce.emovie.data.remote.RemoteConstants.REVENUE
import com.danponce.emovie.data.remote.RemoteConstants.RUNTIME
import com.danponce.emovie.data.remote.RemoteConstants.SIMILAR
import com.danponce.emovie.data.remote.RemoteConstants.STATUS
import com.danponce.emovie.data.remote.RemoteConstants.TAGLINE
import com.danponce.emovie.data.remote.RemoteConstants.TITLE
import com.danponce.emovie.data.remote.RemoteConstants.VIDEO
import com.danponce.emovie.data.remote.RemoteConstants.VIDEOS
import com.danponce.emovie.data.remote.RemoteConstants.VOTE_AVERAGE
import com.danponce.emovie.data.remote.RemoteConstants.VOTE_COUNT

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@Entity(tableName= MOVIE_DETAILS_TABLE)
data class EntityMovieDetail(
    @PrimaryKey @ColumnInfo(name = ID) val id: String,
    @ColumnInfo(name = BACKDROP_PATH) val backdropPath: String,
    @ColumnInfo(name = GENRES) val genres: List<String>,
    @ColumnInfo(name = ORIGINAL_TITLE) val originalTitle: String,
    @ColumnInfo(name = OVERVIEW) val overview: String,
    @ColumnInfo(name = POSTER_PATH) val posterPath: String,
    @ColumnInfo(name = RELEASE_DATE) val releaseDate: String,
    @ColumnInfo(name = RUNTIME) val runtime: Int,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = VOTE_AVERAGE) val voteAverage: Double,
    @ColumnInfo(name = VOTE_COUNT) val voteCount: Int,
    @ColumnInfo(name = VIDEOS) val videos: List<EntityMovieVideo>,
    @ColumnInfo(name = SIMILAR) val similarMovies: List<EntityMovieItem>
)
