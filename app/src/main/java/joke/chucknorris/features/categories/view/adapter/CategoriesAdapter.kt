package joke.chucknorris.features.categories.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import joke.chucknorris.R
import joke.chucknorris.commons.utils.inflate

class CategoriesAdapter(
    val categories: MutableList<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoriesViewHolder(parent.inflate(R.layout.categories_item_view), onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CategoriesViewHolder).bind(categories[position])
    }
}
