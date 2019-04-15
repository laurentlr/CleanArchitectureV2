package com.russier.laurent.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.russier.laurent.R
import com.russier.laurent.domain.Category

class CategoryAdapter constructor(
    private val listener: CategoryClickListener? = null
) :
    RecyclerView.Adapter<CategoryAdapter.BaseViewHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category, parent, false)
        val viewHolder = BaseViewHolder(view)
        listener?.let { categoryListener ->
            view.setOnClickListener { categoryListener.onClickCategory(categories[viewHolder.adapterPosition].id) }
        }
        return viewHolder
    }

    override fun getItemCount() = categories.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        (holder.itemView as TextView).text = categories[position].name
    }

    fun setItems(categories: List<Category>) {
        this.categories.clear()
        this.categories.addAll(categories)
        notifyDataSetChanged()
    }

    class BaseViewHolder(item: View) : RecyclerView.ViewHolder(item)
}
