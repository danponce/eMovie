package com.danponce.emovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.danponce.emovie.data.local.converters.MoviesTypeConverters
import com.danponce.emovie.data.local.dao.MovieDetailDao
import com.danponce.emovie.data.local.dao.MoviesDao
import com.danponce.emovie.data.local.dao.MoviesFavoritesDao
import com.danponce.emovie.data.local.model.EntityFavoriteMovieItem
import com.danponce.emovie.data.local.model.EntityMovieDetail
import com.danponce.emovie.data.local.model.EntityMovieItem
import com.danponce.emovie.data.local.model.EntityMovieVideo


/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@Database(
    entities = [EntityMovieItem::class,
        EntityMovieDetail::class, EntityMovieVideo::class,
               EntityFavoriteMovieItem::class], version = 3, exportSchema = false)
@TypeConverters(MoviesTypeConverters::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun movieDetailDao() : MovieDetailDao
    abstract fun movieFavoritesDao() : MoviesFavoritesDao

}