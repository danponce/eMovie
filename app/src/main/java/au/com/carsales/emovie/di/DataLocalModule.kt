package au.com.carsales.emovie.di

import android.content.Context
import androidx.room.Room
import au.com.carsales.emovie.data.local.DBConstants
import au.com.carsales.emovie.data.local.MoviesDatabase
import au.com.carsales.emovie.data.local.dao.MoviesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Dan on 25, julio, 2022
 * Copyright (c) 2022 Carsales. All rights reserved.
 */
@InstallIn(SingletonComponent::class)
@Module
object DataLocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MoviesDatabase {
        return Room.databaseBuilder(
            appContext,
            MoviesDatabase::class.java,
            DBConstants.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }
}