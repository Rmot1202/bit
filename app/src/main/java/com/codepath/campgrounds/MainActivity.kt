package com.codepath.campgrounds

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.campgrounds.databinding.ActivityMainBinding

// Keep your existing helper if other files use it
// (No network usage now, but leaving it won't hurt)
import kotlinx.serialization.json.Json
fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

private const val TAG = "CampgroundsMain/"


class MainActivity : AppCompatActivity() {
    private lateinit var campgroundsRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding
    private val campgrounds = mutableListOf<Campground>() // <- keep same variable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        campgroundsRecyclerView = findViewById(R.id.campgrounds)
        val campgroundAdapter = CampgroundAdapter(this, campgrounds)
        campgroundsRecyclerView.adapter = campgroundAdapter

        // Layout + divider
        campgroundsRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val divider = DividerItemDecoration(this, it.orientation)
            campgroundsRecyclerView.addItemDecoration(divider)
        }

        // NEW: Add button to insert user-entered items (no API)
        binding.fabAdd.setOnClickListener {
            showAddCampgroundDialog { newCg ->
                // Update list (top)
                campgrounds.add(0, newCg)
                campgroundAdapter.notifyItemInserted(0)
                campgroundsRecyclerView.scrollToPosition(0)
            }
        }

        // (Optional) Seed with one example if you want to see the UI quickly
        // campgrounds.add(Campground(name="Sample Site", description="User-added", imageUrl=null))
        // campgroundAdapter.notifyItemInserted(0)
    }

    private fun showAddCampgroundDialog(onAdd: (Campground) -> Unit) {
        val dialogView = LayoutInflater.from(this)
            .inflate(R.layout.item_campground, null, false)

        val etName = dialogView.findViewById<EditText>(R.id.etName)
        val etDesc = dialogView.findViewById<EditText>(R.id.etDescription)


        AlertDialog.Builder(this)
            .setTitle("Add Campground")
            .setView(dialogView)
            .setPositiveButton("Add") { d, _ ->
                val name = etName.text.toString().trim().ifEmpty { "Untitled" }
                val desc = etDesc.text.toString().trim().ifEmpty { "No description" }

                // Map to YOUR existing Campground fields
                // If your Campground has different property names, set them here.
                val cg = Campground(
                    name = name,
                    description = desc
                    // add/adjust any other fields your adapter expects (ids, codes, etc.)
                )
                onAdd(cg)
                d.dismiss()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
