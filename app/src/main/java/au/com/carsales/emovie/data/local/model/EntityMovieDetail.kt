package au.com.carsales.emovie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.carsales.emovie.data.local.DBConstants
import au.com.carsales.emovie.data.local.DBConstants.MOVIE_DETAILS_TABLE
import au.com.carsales.emovie.data.remote.RemoteConstants.ADULT
import au.com.carsales.emovie.data.remote.RemoteConstants.BACKDROP_PATH
import au.com.carsales.emovie.data.remote.RemoteConstants.BUDGET
import au.com.carsales.emovie.data.remote.RemoteConstants.GENRES
import au.com.carsales.emovie.data.remote.RemoteConstants.HOMEPAGE
import au.com.carsales.emovie.data.remote.RemoteConstants.ID
import au.com.carsales.emovie.data.remote.RemoteConstants.IMDB_ID
import au.com.carsales.emovie.data.remote.RemoteConstants.ORIGINAL_LANGUAGE
import au.com.carsales.emovie.data.remote.RemoteConstants.ORIGINAL_TITLE
import au.com.carsales.emovie.data.remote.RemoteConstants.OVERVIEW
import au.com.carsales.emovie.data.remote.RemoteConstants.POPULARITY
import au.com.carsales.emovie.data.remote.RemoteConstants.POSTER_PATH
import au.com.carsales.emovie.data.remote.RemoteConstants.RELEASE_DATE
import au.com.carsales.emovie.data.remote.RemoteConstants.REVENUE
import au.com.carsales.emovie.data.remote.RemoteConstants.RUNTIME
import au.com.carsales.emovie.data.remote.RemoteConstants.SPOKEN_LANGUAGES
import au.com.carsales.emovie.data.remote.RemoteConstants.STATUS
import au.com.carsales.emovie.data.remote.RemoteConstants.TAGLINE
import au.com.carsales.emovie.data.remote.RemoteConstants.TITLE
import au.com.carsales.emovie.data.remote.RemoteConstants.VIDEO
import au.com.carsales.emovie.data.remote.RemoteConstants.VOTE_AVERAGE
import au.com.carsales.emovie.data.remote.RemoteConstants.VOTE_COUNT
import au.com.carsales.emovie.data.remote.model.MovieItemGenreData
import au.com.carsales.emovie.data.remote.model.MovieSpokenLanguageData

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Entity(tableName= MOVIE_DETAILS_TABLE)
data class EntityMovieDetail(
    @PrimaryKey @ColumnInfo(name = ID) val id: String,
    @ColumnInfo(name = BACKDROP_PATH) val backdropPath: String,
    @ColumnInfo(name = BUDGET) val budget: Int,
    @ColumnInfo(name = GENRES) val genres: List<MovieItemGenreData>,
    @ColumnInfo(name = HOMEPAGE) val homepage: String,
    @ColumnInfo(name = ADULT) val adult: Boolean,
    @ColumnInfo(name = IMDB_ID) val imdbId: String,
    @ColumnInfo(name = ORIGINAL_LANGUAGE) val originalLanguage: String,
    @ColumnInfo(name = ORIGINAL_TITLE) val originalTitle: String,
    @ColumnInfo(name = OVERVIEW) val overview: String,
    @ColumnInfo(name = POPULARITY) val popularity: Double,
    @ColumnInfo(name = POSTER_PATH) val posterPath: String,
    @ColumnInfo(name = RELEASE_DATE) val releaseDate: String,
    @ColumnInfo(name = REVENUE) val revenue: Long,
    @ColumnInfo(name = RUNTIME) val runtime: Int,
    @ColumnInfo(name = STATUS) val status: String,
    @ColumnInfo(name = TAGLINE) val tagline: String,
    @ColumnInfo(name = TITLE) val title: String,
    @ColumnInfo(name = VIDEO) val video: Boolean,
    @ColumnInfo(name = VOTE_AVERAGE) val voteAverage: Double,
    @ColumnInfo(name = VOTE_COUNT) val voteCount: Int
)
