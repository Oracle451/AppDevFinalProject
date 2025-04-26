package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.adapters.MovieAdapter
import com.example.finalproject.network.TmdbApiService
import com.example.finalproject.models.MovieResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //Api set up
    //RV set up
    private lateinit var apiService: TmdbApiService
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MovieAdapter
    private val apiKey = "b3ab2d71922ba0c45d7cfcc04c1fc438"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSearch = findViewById<EditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val btnViewLists = findViewById<Button>(R.id.btnViewLists)
        recyclerView = findViewById(R.id.recyclerViewMovies)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrofit setup
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(TmdbApiService::class.java)

        //Search button handler
        btnSearch.setOnClickListener {
            val query = editTextSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                searchMovies(query)
            } else {
                Toast.makeText(this, "Enter a movie name!", Toast.LENGTH_SHORT).show()
            }
        }

        //View Lists button handler
        btnViewLists.setOnClickListener {
            val intent = Intent(this, ViewListsActivity::class.java) // Specify the target activity
            startActivity(intent) // Start the activity
        }
    }

    //The function that the search button calls
    private fun searchMovies(query: String) {
        apiService.searchMovies(apiKey, query).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                // Handle the response, either on success or on error.
                if (response.isSuccessful) {
                    val movies = response.body()?.results ?: emptyList()
                    adapter = MovieAdapter(movies, "home") { movie ->
                        Toast.makeText(this@MainActivity, "Clicked: ${movie.title}", Toast.LENGTH_SHORT).show()
                    }
                    recyclerView.adapter = adapter
                    recyclerView.visibility = View.VISIBLE
                } else {
                    Toast.makeText(this@MainActivity, "Error fetching data!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed to load data!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
