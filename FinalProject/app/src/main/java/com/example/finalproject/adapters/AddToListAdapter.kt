package com.example.finalproject.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.R
import com.example.finalproject.database.MovieListEntity
import com.example.finalproject.models.Movie

class AddToListAdapter(
    private val movieLists: List<MovieListEntity>,
    private val movie: Movie,
    private val onListSelected: (MovieListEntity, Movie) -> Unit
) : RecyclerView.Adapter<AddToListAdapter.AddToListViewHolder>() {

    inner class AddToListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnList: Button = itemView.findViewById(R.id.btnListItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddToListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return AddToListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AddToListViewHolder, position: Int) {
        val list = movieLists[position]
        holder.btnList.text = list.name
        holder.btnList.setOnClickListener {
            onListSelected(list, movie)
            // Show toast after selection
            Toast.makeText(
                holder.itemView.context,
                "${movie.title} was successfully added to ${list.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int = movieLists.size
}