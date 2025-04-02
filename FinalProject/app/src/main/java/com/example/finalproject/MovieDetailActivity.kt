package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.finalproject.models.Movie

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var tvMoviePoster: ImageView
    private lateinit var tvMovieTitle: TextView
    private lateinit var tvMovieRelease: TextView
    private lateinit var tvMovieOverview: TextView
    private lateinit var tvMovieLength: TextView // Fixed typo: Lenth -> Length

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        // Initialize views
        tvMoviePoster = findViewById(R.id.tvMoviePoster)
        tvMovieTitle = findViewById(R.id.tvMovieTitle)
        tvMovieRelease = findViewById(R.id.tvMovieRelease)
        tvMovieOverview = findViewById(R.id.tvMovieDesc)
        tvMovieLength = findViewById(R.id.tvMovieRating) // Fixed typo

        // Get data from intent (with null safety)
        val movieId = intent.getIntExtra("movieId", -1) // Assuming you pass ID; default to -1 if not present
        val movieTitle = intent.getStringExtra("movieTitle") ?: "Title not available"
        val moviePoster = intent.getStringExtra("moviePoster") ?: "Poster Not Available"
        val movieOverview = intent.getStringExtra("movieOverview") ?: "Overview not available"
        val movieRelease = intent.getStringExtra("movieRelease") ?: "Release date not available"
        val movieRating = intent.getStringExtra("movieRating")?.toFloatOrNull() ?: 0f

        // Reconstruct the Movie object
        val movie = Movie(
            id = movieId,
            title = movieTitle,
            poster_path = moviePoster,
            overview = movieOverview,
            release_date = movieRelease,
            vote_average = movieRating
        )

        // Set the data to views
        tvMovieTitle.text = movieTitle
        tvMoviePoster.load("https://image.tmdb.org/t/p/w500$moviePoster") {
            crossfade(true)
        }
        tvMovieOverview.text = "Overview: $movieOverview"
        tvMovieRelease.text = "Release Date: $movieRelease"
        tvMovieLength.text = "Rating: $movieRating / 10"

        // Back button
        val btnBack: Button = findViewById(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        // Add to List button
        val btnAddToList: Button = findViewById(R.id.btnAddToList)
        btnAddToList.setOnClickListener {
            val intent = Intent(this, AddToListActivity::class.java).apply {
                putExtra("movie", movie)
            }
            startActivity(intent) // No finish(), keep this instance alive
        }
    }
}