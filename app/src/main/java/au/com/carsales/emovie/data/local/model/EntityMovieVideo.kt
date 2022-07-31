package au.com.carsales.emovie.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import au.com.carsales.emovie.data.local.DBConstants
import au.com.carsales.emovie.data.local.DBConstants.MOVIE_VIDEOS_TABLE
import au.com.carsales.emovie.data.remote.RemoteConstants
import au.com.carsales.emovie.data.remote.RemoteConstants.ID
import au.com.carsales.emovie.data.remote.RemoteConstants.KEY
import au.com.carsales.emovie.data.remote.RemoteConstants.NAME
import au.com.carsales.emovie.data.remote.RemoteConstants.PUBLISHED_AT
import au.com.carsales.emovie.data.remote.RemoteConstants.SITE
import au.com.carsales.emovie.data.remote.RemoteConstants.TYPE

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Entity(tableName= MOVIE_VIDEOS_TABLE)
data class EntityMovieVideo(
    @PrimaryKey  @ColumnInfo(name = ID) val id: String,
    @ColumnInfo(name = KEY) val key: String,
    @ColumnInfo(name = NAME) val name: String,
    @ColumnInfo(name = PUBLISHED_AT) val publishedAt: String,
    @ColumnInfo(name = SITE) val site: String,
    @ColumnInfo(name = TYPE) val type: String,
)
