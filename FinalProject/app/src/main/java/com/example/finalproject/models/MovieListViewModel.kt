package com.example.finalproject.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.finalproject.database.MovieListEntity
import com.example.finalproject.repository.MovieListRepository
import kotlinx.coroutines.flow.Flow

class MovieListViewModel(private val repository: MovieListRepository) : ViewModel() {
    fun addList(name: String) {
        viewModelScope.launch {
            repository.addList(name)
        }
    }

    private val _movieLists = MutableLiveData<List<MovieListEntity>>()
    val movieLists: LiveData<List<MovieListEntity>> = _movieLists

    fun getLists() {
        viewModelScope.launch {
            val lists = repository.getLists()
            _movieLists.value = lists
        }
    }

    fun deleteList(name: String) {
        viewModelScope.launch {
            repository.deleteList(name)
        }
    }
}
