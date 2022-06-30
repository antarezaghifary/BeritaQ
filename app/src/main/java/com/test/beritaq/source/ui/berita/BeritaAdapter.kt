package com.test.beritaq.source.ui.berita

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.beritaq.databinding.AdapterBeritaBinding
import com.test.beritaq.source.berita.ArticlesItem

class BeritaAdapter(
    val artikels: ArrayList<ArticlesItem>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {
    class ViewHolder(val binding: AdapterBeritaBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnAdapterListener {
        fun onClick(artikel: ArticlesItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterBeritaBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val artikes = artikels[position]
        holder.binding.tvJudul.text = artikes.title
        holder.binding.tvPublishedAt.text = artikes.publishedAt
        holder.itemView.setOnClickListener {
            listener.onClick(artikes)
        }
    }

    override fun getItemCount() = artikels.size

    @SuppressLint("NotifyDataSetChanged")
    fun addData(data: List<ArticlesItem>) {
        artikels.clear()
        artikels.addAll(data)
        notifyDataSetChanged()
    }

}