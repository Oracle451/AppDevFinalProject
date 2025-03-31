package com.example.finalproject.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_lists")
data class MovieListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)