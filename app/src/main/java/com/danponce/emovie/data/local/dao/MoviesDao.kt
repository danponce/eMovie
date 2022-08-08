package com.danponce.emovie.data.local.dao

import androidx.room.*
import com.danponce.emovie.data.local.DBConstants.MOVIES_TABLE
import com.danponce.emovie.data.local.model.EntityMovieItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovie(movie: EntityMovieItem?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<EntityMovieItem>)

    @Query("select * from $MOVIES_TABLE")
    fun getAllMovies(): Flow<List<EntityMovieItem?>?>

    @Query("select * from $MOVIES_TABLE where vote_average >= 8.5")
    fun getTopRatedMovies() : Flow<List<EntityMovieItem?>?>

    @Query("select * from $MOVIES_TABLE where release_date > :fromDate")
    fun getUpcomingMovies(fromDate: String) : Flow<List<EntityMovieItem?>?>

    @Delete
    fun delete(favoriteTVShow: EntityMovieItem?)
}