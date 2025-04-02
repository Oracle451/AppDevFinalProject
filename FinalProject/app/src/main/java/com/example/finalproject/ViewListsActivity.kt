package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.MovieListAdapter
import com.example.finalproject.database.MovieListDatabase
import com.example.finalproject.models.MovieListViewModel
import com.example.finalproject.models.MovieListViewModelFactory
import com.example.finalproject.repository.MovieListRepository

class ViewListsActivity : AppCompatActivity() {

    private lateinit var btnBack: Button
    private lateinit var btnNewList: Button
    private lateinit var rvMovieLists: RecyclerView
    private lateinit var movieListAdapter: MovieListAdapter
    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var repository: MovieListRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_lists) // Ensure this layout exists

        // Initialize UI components
        btnBack = findViewById(R.id.btnBack)
        btnNewList = findViewById(R.id.btnNewList)
        rvMovieLists = findViewById(R.id.rvMovieLists) // Ensure RecyclerView is in your XML

        // Initialize ViewModel
        val dao = MovieListDatabase.getDatabase(application).movieListDao()
        repository = MovieListRepository(dao)
        val factory = MovieListViewModelFactory(repository)
        movieListViewModel = ViewModelProvider(this, factory)[MovieListViewModel::class.java]

        // Set up RecyclerView with an empty list initially
        movieListAdapter = MovieListAdapter(emptyList()) { list ->
            val intent = Intent(this, MovieListActivity::class.java).apply {
                putExtra("LIST_NAME", list.name) // Pass the list name to the next activity
            }
            startActivity(intent)

            finish()
        }
        rvMovieLists.layoutManager = LinearLayoutManager(this)
        rvMovieLists.adapter = movieListAdapter

        // Observe movie lists from ViewModel
        movieListViewModel.movieLists.observe(this) { lists ->
            movieListAdapter.updateLists(lists)
        }

        // Load lists from Room Database
        movieListViewModel.getLists()

        // Set button listeners
        btnBack.setOnClickListener {
            finish()
        }

        btnNewList.setOnClickListener {
            val intent = Intent(this, MakeListActivity::class.java) // Specify the target activity
            startActivity(intent) // Start the activity

            finish()
        }
    }
    private fun showCreateListDialog() {
        val dialog = android.app.AlertDialog.Builder(this)
        dialog.setTitle("Enter List Name")

        val input = android.widget.EditText(this)
        input.hint = "List Name"
        dialog.setView(input)

        dialog.setPositiveButton("Create") { _, _ ->
            val listName = input.text.toString().trim()
            if (listName.isNotEmpty()) {
                movieListViewModel.addList(listName)
                movieListViewModel.getLists()
            } else {
                Toast.makeText(this, "List name cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.setNegativeButton("Cancel", null)
        dialog.show()
    }
}