package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import coil.load

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list) // Ensure this layout exists

        // Find the back button by ID
        val btnBack: Button = findViewById(R.id.btnBack)

        // Set up the onClickListener to navigate back to MainActivity
        btnBack.setOnClickListener {
            // Create an intent to start MainActivity
            val intent = Intent(this, ViewListsActivity::class.java)
            startActivity(intent)
            finish() // Finish ViewListsActivity so the user can't return to it by pressing back
        }

        val movieName: TextView = findViewById(R.id.tvMovieTitle)
        val moviePoster: ImageView = findViewById(R.id.tvMoviePoster)

        val movieName2: TextView = findViewById(R.id.tvMovieTitle2)
        val moviePoster2: ImageView = findViewById(R.id.tvMoviePoster2)

        // Example data
        val movieTitle = "Movie Name"
        val posterUrl = R.drawable.placeholder // Replace with actual image URL

        // Set the movie title text
        movieName.text = movieTitle

        // Load the image into the ImageView using Coil
        moviePoster.load(posterUrl) {
            crossfade(true) // Optional: Enable crossfade for smooth image loading
        }

        // Set the movie title text
        movieName2.text = movieTitle

        // Load the image into the ImageView using Coil
        moviePoster2.load(posterUrl) {
            crossfade(true) // Optional: Enable crossfade for smooth image loading
        }

    }
}
