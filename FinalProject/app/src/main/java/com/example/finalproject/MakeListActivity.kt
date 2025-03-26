package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class MakeListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the correct layout for ViewListsActivity
        setContentView(R.layout.activity_make_list) // Ensure this layout exists

        // Find the back button by ID
        val btnBack: Button = findViewById(R.id.btnBack)

        // Set up the onClickListener to navigate back to MainActivity
        btnBack.setOnClickListener {
            // Create an intent to start MainActivity
            val intent = Intent(this, ViewListsActivity::class.java)
            startActivity(intent)
            finish() // Finish ViewListsActivity so the user can't return to it by pressing back
        }
    }
}
