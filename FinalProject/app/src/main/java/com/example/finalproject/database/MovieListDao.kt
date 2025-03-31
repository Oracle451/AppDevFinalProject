package com.example.finalproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(movieList: MovieListEntity)

    @Query("SELECT * FROM movie_lists")
    fun getAllLists(): List<MovieListEntity>

    @Query("DELETE FROM movie_lists WHERE name = :listName")
    suspend fun deleteList(listName: String)
}