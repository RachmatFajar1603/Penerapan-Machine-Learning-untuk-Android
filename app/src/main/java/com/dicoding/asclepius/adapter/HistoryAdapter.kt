package com.dicoding.asclepius.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.asclepius.database.History
import com.dicoding.asclepius.databinding.ItemHistoryBinding

class HistoryAdapter : ListAdapter<History, HistoryAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: NewsAdapter.OnItemClickCallback



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MyViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History) {
            binding.tvNameHistory.text = "${item.label}: ${item.score}"
            Glide.with(itemView.context)
                .load(item.image)
                .into(binding.ivImageHistory)
        }
    }



    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(
                oldItem: History, newItem: History
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: History, newItem: History
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
