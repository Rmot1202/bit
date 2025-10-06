import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.campgrounds.Campground
import com.codepath.campgrounds.R

// com/codepath/campgrounds/CampgroundAdapter.kt
class CampgroundAdapter(
    private val context: Context,
    private val items: List<Campground>
) : RecyclerView.Adapter<CampgroundAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.campgroundName)
        private val desc: TextView = itemView.findViewById(R.id.campgroundDescription)
        private val sleep: TextView = itemView.findViewById(R.id.Sleep)

        fun bind(item: Campground) {
            name.text = item.name ?: ""
            desc.text = item.description ?: ""
            sleep.text = item.sleep ?: ""
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_campground, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount() = items.size
}
