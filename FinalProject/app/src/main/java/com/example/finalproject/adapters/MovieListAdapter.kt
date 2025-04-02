package com.example.finalproject.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.database.MovieListEntity
import com.example.finalproject.R
import com.example.finalproject.MovieListActivity

class MovieListAdapter(
    private var movieLists: List<MovieListEntity>,
    private val onListClicked: (MovieListEntity) -> Unit = {}
) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    inner class MovieListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnList: Button = itemView.findViewById(R.id.btnListItem)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false)
        return MovieListViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val list = movieLists[position]
        holder.btnList.text = list.name
        holder.btnList.setOnClickListener {
            // Trigger the onListClicked callback
            onListClicked(list)

            // Start MovieListActivity with the selected list
            val context = holder.itemView.context
            val intent = Intent(context, MovieListActivity::class.java).apply {
                putExtra("movieListEntity", list)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movieLists.size

    fun updateLists(newLists: List<MovieListEntity>) {
        movieLists = newLists
        notifyDataSetChanged()
    }
}