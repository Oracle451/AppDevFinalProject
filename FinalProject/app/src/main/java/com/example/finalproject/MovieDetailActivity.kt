package com.example.finalproject

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import coil.load

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var tvMoviePoster: ImageView
    private lateinit var tvMovieTitle: TextView
    private lateinit var tvMovieRelease: TextView
    private lateinit var tvMovieOverview: TextView
    private lateinit var tvMovieLenth: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Initialize views
        tvMoviePoster = findViewById(R.id.tvMoviePoster)
        tvMovieTitle = findViewById(R.id.tvMovieTitle)
        tvMovieRelease = findViewById(R.id.tvMovieRelease)
        tvMovieOverview = findViewById(R.id.tvMovieDesc)
        tvMovieLenth = findViewById(R.id.tvMovieRating)

        // Get data from intent (with null safety)
        val movieTitle = intent.getStringExtra("movieTitle") ?: "Title not available"
        val moviePoster = intent.getStringExtra("moviePoster") ?: "Poster Not Available"
        val movieOverview = intent.getStringExtra("movieOverview") ?: "Overview not available"
        val movieRelease = intent.getStringExtra("movieRelease") ?: "Release date not available"
        val movieRating = intent.getStringExtra("movieRating") ?: "N/A"

        // Set the data to views
        tvMovieTitle.text = movieTitle

        tvMoviePoster.load("https://image.tmdb.org/t/p/w500$moviePoster") {
            crossfade(true)
        }

        tvMovieOverview.text = "Overview: $movieOverview"
        tvMovieRelease.text = "Release Date: $movieRelease"
        tvMovieLenth.text = "Rating: $movieRating / 10"

        val btnBack: Button = findViewById(R.id.btnBack)

        btnBack.setOnClickListener {
            // Start MainActivity and finish current activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnAddToList: Button = findViewById(R.id.btnAddToList)

        btnAddToList.setOnClickListener {
            val intent = Intent(this, AddToListActivity::class.java) // Specify the target activity
            startActivity(intent) // Start the activity
        }
    }
}

