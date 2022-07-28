package au.com.carsales.emovie.data.local.dao

import androidx.room.*
import au.com.carsales.emovie.data.local.DBConstants.MOVIES_TABLE
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Dao
interface MoviesDao {

    @Insert
    fun addMovie(movie: EntityMovieItem?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<EntityMovieItem>)

    @Query("select * from $MOVIES_TABLE")
    fun getAllMovies(): List<EntityMovieItem?>?

    @Query("select * from $MOVIES_TABLE where vote_average > 8.5")
    fun getTopRatedMovies() : List<EntityMovieItem?>?

    @Query("select * from $MOVIES_TABLE where release_date > 2022-06-29")
    fun getUpcomingMovies() : List<EntityMovieItem?>?

    @Query("SELECT EXISTS (SELECT 1 FROM $MOVIES_TABLE WHERE id=:id)")
    fun getMovie(id: Int): Int

    @Delete
    fun delete(favoriteTVShow: EntityMovieItem?)
}