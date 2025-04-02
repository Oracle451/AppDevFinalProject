package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.MovieAdapter
import com.example.finalproject.database.MovieListDatabase
import com.example.finalproject.database.MovieListEntity
import com.example.finalproject.models.MovieListViewModel
import com.example.finalproject.repository.MovieListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListActivity : AppCompatActivity() {

    private lateinit var repository: MovieListRepository
    private lateinit var movieListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list)

        // Initialize repository and view model
        val dao = MovieListDatabase.getDatabase(application).movieListDao()
        repository = MovieListRepository(dao)
        movieListViewModel = MovieListViewModel(repository)

        // Find views
        val btnBack: Button = findViewById(R.id.btnBack)
        val deleteListBtn: Button = findViewById(R.id.deleteListBtn)
        val renameListBtn: Button = findViewById(R.id.renameListBtn)
        val tvListName: TextView = findViewById(R.id.tvListName)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewMovies)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get the list ID from the intent
        val movieListEntityFromIntent = intent.getParcelableExtra<MovieListEntity>("movieListEntity")
        val listId = movieListEntityFromIntent?.id

        // Fetch the latest MovieListEntity from the database
        if (listId != null) {
            CoroutineScope(Dispatchers.Main).launch {
                val movieListEntity = repository.getListById(listId)
                val movies = movieListEntity?.movies ?: emptyList()
                println("Movies fetched: $movies")

                // Set adapter with the movies
                val adapter = MovieAdapter(movies) { movie -> }
                recyclerView.adapter = adapter
                recyclerView.visibility = View.VISIBLE // Make RecyclerView visible

                // Update the list name TextView
                movieListEntity?.let {
                    tvListName.text = it.name
                }

                // Delete List button click listener
                deleteListBtn.setOnClickListener {
                    movieListEntity?.let { entity ->
                        CoroutineScope(Dispatchers.Main).launch {
                            repository.deleteListById(entity.id)
                            val intent = Intent(this@MovieListActivity, ViewListsActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }

                // Rename button click listener
                renameListBtn.setOnClickListener {
                    movieListEntity?.let { entity ->
                        val editText = EditText(this@MovieListActivity).apply {
                            setText(entity.name)
                        }
                        AlertDialog.Builder(this@MovieListActivity)
                            .setTitle("Rename List")
                            .setView(editText)
                            .setPositiveButton("Save") { _, _ ->
                                val newName = editText.text.toString().trim()
                                if (newName.isNotEmpty()) {
                                    CoroutineScope(Dispatchers.Main).launch {
                                        repository.updateListName(entity.id, newName)
                                        tvListName.text = newName
                                    }
                                }
                            }
                            .setNegativeButton("Cancel", null)
                            .show()
                    }
                }
            }
        } else {
            val movies = movieListEntityFromIntent?.movies ?: emptyList()
            val adapter = MovieAdapter(movies) { movie -> }
            recyclerView.adapter = adapter
            recyclerView.visibility = View.VISIBLE // Make visible in fallback too
            movieListEntityFromIntent?.let { tvListName.text = it.name }
        }

        // Back button click listener
        btnBack.setOnClickListener {
            val intent = Intent(this, ViewListsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}