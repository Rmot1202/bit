package com.codepath.campgrounds

import android.os.Bundle

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val TAG = "CampgroundDetailActivity"
const val CAMPGROUND_EXTRA = "CAMPGROUND_EXTRA"

class DetailActivity : AppCompatActivity() {
    private lateinit var campgroundNameTV: TextView
    private lateinit var campgroundDescriptionTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Find the views for the screen
        campgroundNameTV = findViewById(R.id.campgroundName)
        campgroundDescriptionTV = findViewById(R.id.campgroundDescription)

        // Get the extra from the Intent
        val campground = intent.getSerializableExtra(CAMPGROUND_EXTRA) as Campground

        // Set the name, location, and description information
        campgroundNameTV.text = campground.name
        campgroundDescriptionTV.text = campground.description
    }
}
