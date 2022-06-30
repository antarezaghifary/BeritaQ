package com.test.beritaq.source.ui.berita

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.beritaq.R
import com.test.beritaq.databinding.AdapterCategoryBinding
import com.test.beritaq.source.berita.CategoryModel

class CategoryAdapter(
    val kategoris: List<CategoryModel>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private val items = arrayListOf<TextView>()

    class ViewHolder(val binding: AdapterCategoryBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnAdapterListener {
        fun onClick(category: CategoryModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = kategoris[position]
        holder.binding.chipCategory.text = category.nama
        items.add(holder.binding.chipCategory)
        holder.itemView.setOnClickListener {
            listener.onClick(category)
            setColorChip(holder.binding.chipCategory)
        }
        setColorChip(items[0])
    }

    override fun getItemCount() = kategoris.size

    private fun setColorChip(tvColorChip: TextView) {
        items.forEach { it.setBackgroundResource(R.color.white) }
        tvColorChip.setBackgroundResource(android.R.color.darker_gray)
    }
}