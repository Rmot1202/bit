// com/codepath/campgrounds/MainActivity.kt
package com.codepath.campgrounds

import CampgroundAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.campgrounds.databinding.ActivityMainBinding

private const val TAG = "CampgroundsMain/"

class MainActivity : AppCompatActivity() {
    private lateinit var campgroundsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val campgrounds = mutableListOf<Campground>()   // same variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        campgroundsRecyclerView = findViewById(R.id.campgrounds)
        val campgroundAdapter = CampgroundAdapter(this, campgrounds)
        campgroundsRecyclerView.adapter = campgroundAdapter

        campgroundsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            campgroundsRecyclerView.addItemDecoration(
                DividerItemDecoration(this, it.orientation)
            )
        }

        // User input instead of API:
        binding.fabAdd.setOnClickListener {
            val dialogView = LayoutInflater.from(this)
                .inflate(R.layout.activity_detail, null, false)
            val etName = dialogView.findViewById<EditText>(R.id.etName)
            val etDesc = dialogView.findViewById<EditText>(R.id.etDescription_Calories)
            val sleepSlider = dialogView.findViewById<com.google.android.material.slider.Slider>(R.id.sleepSlider)


            AlertDialog.Builder(this)
                .setTitle("Add Campground")
                .setView(dialogView)
                .setPositiveButton("Add") { d, _ ->
                    val name = etName.text.toString().trim().ifEmpty { "Untitled" }
                    val desc = etDesc.text.toString().trim().ifEmpty { "No description" }
                    val sleep = sleepSlider.value.toString().trim().ifEmpty { "No sleep" }
                    val cg = Campground(name = name, description = desc, sleep = sleep)
                    campgrounds.add(0, cg)
                    campgroundAdapter.notifyItemInserted(0)
                    campgroundsRecyclerView.scrollToPosition(0)
                    d.dismiss()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }
}
