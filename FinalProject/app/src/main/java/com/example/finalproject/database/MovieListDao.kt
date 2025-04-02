package com.example.finalproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(movieList: MovieListEntity)

    @Query("SELECT * FROM movie_lists")
    fun getAllLists(): List<MovieListEntity>

    @Query("DELETE FROM movie_lists WHERE name = :listName")
    suspend fun deleteList(listName: String)

    @Query("UPDATE movie_lists SET name = :newName WHERE id = :id")
    suspend fun updateListName(id: Int, newName: String)

    @Update
    suspend fun updateList(list: MovieListEntity)

    @Query("SELECT * FROM movie_lists WHERE id = :id")
    suspend fun getListById(id: Int): MovieListEntity?

    @Query("DELETE FROM movie_lists WHERE id = :id")
    suspend fun deleteListById(id: Int)
}