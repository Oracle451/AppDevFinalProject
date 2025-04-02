package com.example.finalproject.repository

import com.example.finalproject.database.MovieListDao
import com.example.finalproject.database.MovieListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieListRepository(private val movieListDao: MovieListDao) {
    suspend fun addList(name: String) {
        movieListDao.insertList(MovieListEntity(name = name, movies = emptyList()))
    }

    suspend fun getLists(): List<MovieListEntity> = withContext(Dispatchers.IO) {
        movieListDao.getAllLists()
    }

    suspend fun deleteList(name: String) {
        movieListDao.deleteList(name)
    }

    suspend fun updateListName(id: Int, newName: String) {
        withContext(Dispatchers.IO) {
            movieListDao.updateListName(id, newName)
        }
    }

    suspend fun updateList(list: MovieListEntity) {
        withContext(Dispatchers.IO) {
            movieListDao.updateList(list)
        }
    }

    suspend fun getListById(id: Int): MovieListEntity? = withContext(Dispatchers.IO) {
        movieListDao.getListById(id)
    }

    suspend fun deleteListById(id: Int) {
        withContext(Dispatchers.IO) {
            movieListDao.deleteListById(id)
        }
    }
}
