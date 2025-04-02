package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.AddToListAdapter
import com.example.finalproject.database.MovieListDatabase
import com.example.finalproject.database.MovieListEntity
import com.example.finalproject.models.Movie
import com.example.finalproject.repository.MovieListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddToListActivity : AppCompatActivity() {

    private lateinit var repository: MovieListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_list)

        // Initialize repository
        repository = MovieListRepository(MovieListDatabase.getDatabase(applicationContext).movieListDao())

        // Find views
        val btnBack: Button = findViewById(R.id.btnBack)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLists)

        // Get the Movie from intent
        val movie = intent.getParcelableExtra<Movie>("movie") ?: run {
            finish() // Close activity if no movie is provided
            return
        }

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch lists and set adapter
        CoroutineScope(Dispatchers.Main).launch {
            val movieLists = repository.getLists()
            val adapter = AddToListAdapter(movieLists, movie) { list, selectedMovie ->
                // Add movie to the selected list
                val updatedMovies = list.movies.toMutableList().apply { add(selectedMovie) }
                val updatedList = list.copy(movies = updatedMovies)
                CoroutineScope(Dispatchers.Main).launch {
                    repository.updateList(updatedList)
                    finish() // Close activity after adding
                }
            }
            recyclerView.adapter = adapter
        }

        // Back button click listener
        btnBack.setOnClickListener {
            finish() // Simply return to the previous activity
        }
    }
}