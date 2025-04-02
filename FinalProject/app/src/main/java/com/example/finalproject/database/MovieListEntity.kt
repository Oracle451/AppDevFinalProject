package com.example.finalproject.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.finalproject.models.Movie
import kotlinx.parcelize.Parcelize
import androidx.room.TypeConverters

@Parcelize
@Entity(tableName = "movie_lists")
@TypeConverters(Converters::class)
data class MovieListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val movies: List<Movie>
) : Parcelable