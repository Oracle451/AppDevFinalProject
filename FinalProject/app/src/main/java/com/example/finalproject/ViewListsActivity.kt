package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class ViewListsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the correct layout for ViewListsActivity
        setContentView(R.layout.view_lists) // Ensure this layout exists

        // Find the back button by ID
        val btnBack: Button = findViewById(R.id.btnBack)

        // Set up the onClickListener to navigate back to MainActivity
        btnBack.setOnClickListener {
            // Create an intent to start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish ViewListsActivity so the user can't return to it by pressing back
        }

        val listbtn: Button = findViewById(R.id.list)

        listbtn.setOnClickListener {
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnNewList: Button = findViewById(R.id.btnNewList)

        btnNewList.setOnClickListener {
            val intent = Intent(this, MakeListActivity::class.java) // Specify the target activity
            startActivity(intent) // Start the activity
        }
    }
}
