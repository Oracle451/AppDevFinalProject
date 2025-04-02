package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import com.example.finalproject.R
import com.example.finalproject.ViewListsActivity
import com.example.finalproject.database.MovieListDatabase
import com.example.finalproject.models.MovieListViewModel
import com.example.finalproject.repository.MovieListRepository
import android.widget.Toast

class MakeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the correct layout for ViewListsActivity
        setContentView(R.layout.activity_make_list) // Ensure this layout exists

        // Find the back button by ID
        val btnBack: Button = findViewById(R.id.btnBack)
        val etListName: EditText = findViewById(R.id.etListName)
        val btnMakeList: Button = findViewById(R.id.btnMakeList)

        val dao = MovieListDatabase.getDatabase(application).movieListDao()
        val repository = MovieListRepository(dao)
        val movieListViewModel = MovieListViewModel(repository)

        btnMakeList.setOnClickListener {
            val listName = etListName.text.toString().trim()

            if (listName.isNotEmpty()) {
                movieListViewModel.addList(listName)
                Toast.makeText(this, "List '$listName' created!", Toast.LENGTH_SHORT).show()
                etListName.text.clear()
            } else {
                Toast.makeText(this, "Please enter a list name.", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up the onClickListener to navigate back to MainActivity
        btnBack.setOnClickListener {
            val intent = Intent(this@MakeListActivity, ViewListsActivity::class.java)
            startActivity(intent)

            finish() // Finish ViewListsActivity so the user can't return to it by pressing back
        }
    }
}
