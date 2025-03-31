package com.example.finalproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieListEntity::class], version = 1, exportSchema = false)
abstract class MovieListDatabase : RoomDatabase() {
    abstract fun movieListDao(): MovieListDao

    companion object {
        @Volatile
        private var INSTANCE: MovieListDatabase? = null

        fun getDatabase(context: Context): MovieListDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieListDatabase::class.java,
                    "movie_list_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
