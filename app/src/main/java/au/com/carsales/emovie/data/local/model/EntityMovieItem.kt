package au.com.carsales.emovie.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.carsales.emovie.data.local.DBConstants.MOVIES_TABLE
import au.com.carsales.emovie.data.remote.RemoteConstants.ID
import au.com.carsales.emovie.data.remote.RemoteConstants.IS_FAVORITE
import au.com.carsales.emovie.data.remote.RemoteConstants.ORIGINAL_LANGUAGE
import au.com.carsales.emovie.data.remote.RemoteConstants.ORIGINAL_TITLE
import au.com.carsales.emovie.data.remote.RemoteConstants.POSTER_PATH
import au.com.carsales.emovie.data.remote.RemoteConstants.RELEASE_DATE
import au.com.carsales.emovie.data.remote.RemoteConstants.VOTE_AVERAGE
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
@Entity(tableName= MOVIES_TABLE)
data class EntityMovieItem (
    @PrimaryKey @ColumnInfo(name = ID) val id: String,
    @ColumnInfo(name = POSTER_PATH) val posterPath: String,
    @ColumnInfo(name = ORIGINAL_TITLE) val originalTitle: String,
    @ColumnInfo(name = VOTE_AVERAGE) val voteAverage: Double,
    @ColumnInfo(name = RELEASE_DATE) val releaseDate: String,
    @ColumnInfo(name = ORIGINAL_LANGUAGE) val originalLanguage: String
) : Parcelable