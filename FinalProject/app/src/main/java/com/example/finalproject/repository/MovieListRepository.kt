package com.example.finalproject.repository

import com.example.finalproject.database.MovieListDao
import com.example.finalproject.database.MovieListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieListRepository(private val movieListDao: MovieListDao) {
    suspend fun addList(name: String) {
        movieListDao.insertList(MovieListEntity(name = name))
    }

    suspend fun getLists(): List<MovieListEntity> = withContext(Dispatchers.IO) {
        movieListDao.getAllLists()
    }

    suspend fun deleteList(name: String) {
        movieListDao.deleteList(name)
    }
}
