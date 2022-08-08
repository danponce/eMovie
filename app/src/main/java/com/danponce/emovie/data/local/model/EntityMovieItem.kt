package com.danponce.emovie.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danponce.emovie.data.local.DBConstants.MOVIES_TABLE
import com.danponce.emovie.data.remote.RemoteConstants.ID
import com.danponce.emovie.data.remote.RemoteConstants.IS_FAVORITE
import com.danponce.emovie.data.remote.RemoteConstants.ORIGINAL_LANGUAGE
import com.danponce.emovie.data.remote.RemoteConstants.ORIGINAL_TITLE
import com.danponce.emovie.data.remote.RemoteConstants.POSTER_PATH
import com.danponce.emovie.data.remote.RemoteConstants.RELEASE_DATE
import com.danponce.emovie.data.remote.RemoteConstants.VOTE_AVERAGE
import kotlinx.parcelize.Parcelize

/**
 * Created by Dan on 25, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Parcelize
@Entity(tableName= MOVIES_TABLE)
data class EntityMovieItem (
    @PrimaryKey @ColumnInfo(name = ID) override val id: String,
    @ColumnInfo(name = POSTER_PATH) override val posterPath: String,
    @ColumnInfo(name = ORIGINAL_TITLE) override val originalTitle: String,
    @ColumnInfo(name = VOTE_AVERAGE) override val voteAverage: Double,
    @ColumnInfo(name = RELEASE_DATE) override val releaseDate: String,
    @ColumnInfo(name = ORIGINAL_LANGUAGE) override val originalLanguage: String
) : BaseMovieEntity(), Parcelable