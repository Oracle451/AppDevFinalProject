package com.example.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.MovieDetailActivity
import com.example.finalproject.R
import com.example.finalproject.databinding.ItemMovieBinding
import com.example.finalproject.models.Movie
import coil.load

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class MovieViewHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.tvMovieTitle.text = movie.title

            binding.tvMoviePoster.load(movie.poster_path?.let { "https://image.tmdb.org/t/p/w500$it" }) {
                placeholder(R.drawable.placeholder)
                crossfade(true)
            }

            binding.root.setOnClickListener {
                // Passing the movie object to MovieDetailActivity
                val context = itemView.context
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra("movieTitle", movie.title)
                intent.putExtra("moviePoster", movie.poster_path)
                intent.putExtra("movieOverview", movie.overview)
                intent.putExtra("movieRelease", movie.release_date)
                intent.putExtra("movieRating", movie.vote_average.toString())
                context.startActivity(intent)
            }
        }
    }
}
