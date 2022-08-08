package com.danponce.emovie.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.danponce.emovie.data.local.DBConstants
import com.danponce.emovie.data.local.DBConstants.MOVIE_DETAILS_TABLE
import com.danponce.emovie.data.local.model.EntityMovieDetail
import com.danponce.emovie.data.local.model.EntityMovieItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Dan on 30, julio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
@Dao
interface MovieDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovieDetail(detail: EntityMovieDetail?)

    @Query("SELECT * FROM $MOVIE_DETAILS_TABLE WHERE id = :id")
    fun getMovieDetail(id: String): Flow<EntityMovieDetail?>

}