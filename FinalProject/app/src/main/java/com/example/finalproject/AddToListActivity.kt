package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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

                //Basic if statement for preventing existing movies from being added to the same list.
                //If it doesn't exist, add it to the list.
                if (!list.movies.toMutableList().contains(selectedMovie)) {
                    val updatedMovies = list.movies.toMutableList().apply { add(selectedMovie) }
                    val updatedList = list.copy(movies = updatedMovies)
                    CoroutineScope(Dispatchers.Main).launch {
                        repository.updateList(updatedList)
                        finish() // Close activity after adding
                    }
                    //Let user know adding was successful.
                    Toast.makeText(
                        this@AddToListActivity,
                        "${selectedMovie.title} was successfully added to ${list.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                //If it did exist, don't
                } else {
                    //Let user know the outcome.
                    Toast.makeText(
                        this@AddToListActivity,
                        "${selectedMovie.title} is already in ${list.name}",
                        Toast.LENGTH_SHORT
                    ).show()
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