package joke.chucknorris.features.categories.view.adapter

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.categories_item_view.view.*

@SuppressLint("ViewConstructor")
class CategoriesViewHolder(
    itemView: View,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(category: String) {
        itemView.tvCategory.text = category

        itemView.cardView.setOnClickListener {
            onClick.invoke(category)

        }
    }
}