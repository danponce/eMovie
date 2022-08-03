package au.com.carsales.emovie.data.local.dao

import androidx.room.*
import au.com.carsales.emovie.data.local.DBConstants.MOVIES_TABLE
import au.com.carsales.emovie.data.local.DBConstants.MOVIE_FAVORITES
import au.com.carsales.emovie.data.local.model.EntityFavoriteMovieItem
import au.com.carsales.emovie.data.local.model.EntityMovieItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@Dao
interface MoviesFavoritesDao {

    @Insert
    fun addFavoriteMovie(movie: EntityFavoriteMovieItem?)

    @Query("select * from $MOVIE_FAVORITES")
    fun getAllFavoriteMovies(): Flow<List<EntityFavoriteMovieItem?>?>

    @Query("SELECT EXISTS (SELECT 1 FROM $MOVIE_FAVORITES WHERE id=:id)")
    fun isFavorite(id: String): Flow<Boolean>

    @Delete
    fun deleteFavoriteMovie(movie: EntityFavoriteMovieItem?)
}