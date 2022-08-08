package com.danponce.emovie.data.local.converters

import androidx.room.TypeConverter
import com.danponce.emovie.data.local.model.EntityMovieVideo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


/**
 * Created by Dan on 27, junio, 2022
 * Copyright (c) 2022. All rights reserved.
 */
class MoviesTypeConverters {

    @TypeConverter
    fun fromStringToList(value: String?): List<String?>? {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun restoreEntityMovieList(entityMovieList: String?): List<EntityMovieVideo?>? {
        return Gson().fromJson(entityMovieList, object : TypeToken<List<EntityMovieVideo?>?>() {}.type)
    }

    @TypeConverter
    fun saveEntityMovieList(entityMovieList: List<EntityMovieVideo?>?): String? {
        return Gson().toJson(entityMovieList)
    }
}