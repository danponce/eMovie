package au.com.carsales.emovie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.carsales.emovie.data.local.DBConstants.MOVIE_GENRES_TABLE
import au.com.carsales.emovie.data.remote.RemoteConstants.ID
import au.com.carsales.emovie.data.remote.RemoteConstants.NAME

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Entity(tableName= MOVIE_GENRES_TABLE)
data class EntityMovieGenre (
    @PrimaryKey @ColumnInfo(name = ID) val id: String,
    @ColumnInfo(name = NAME)val name: String
    )
