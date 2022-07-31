package au.com.carsales.emovie.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import au.com.carsales.emovie.data.local.converters.MoviesTypeConverters
import au.com.carsales.emovie.data.local.dao.MovieDetailDao
import au.com.carsales.emovie.data.local.dao.MoviesDao
import au.com.carsales.emovie.data.local.model.EntityMovieDetail
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import au.com.carsales.emovie.data.local.model.EntityMovieVideo


/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Database(
    entities = [EntityMovieItem::class,
        EntityMovieDetail::class, EntityMovieVideo::class], version = 2, exportSchema = false)
@TypeConverters(MoviesTypeConverters::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    abstract fun movieDetailDao() : MovieDetailDao
}