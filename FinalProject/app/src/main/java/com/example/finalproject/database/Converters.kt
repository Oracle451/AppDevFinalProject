package com.example.finalproject.database

import androidx.room.TypeConverter
import com.example.finalproject.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromMovieList(movies: List<Movie>?): String? {
        return if (movies == null) null else gson.toJson(movies)
    }

    @TypeConverter
    fun toMovieList(json: String?): List<Movie>? {
        if (json == null) return null
        val listType = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(json, listType)
    }
}