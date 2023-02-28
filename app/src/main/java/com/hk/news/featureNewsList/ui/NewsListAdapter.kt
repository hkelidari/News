package com.hk.news.featureNewsList.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.hk.news.databinding.ItemNewsBinding
import com.hk.news.featureNewsList.domain.model.News

class NewsListAdapter(
    private val onItemClick: (News) -> Unit
) : ListAdapter<News, NewsListAdapter.ViewHolder>(
    object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.listItem.setOnClickListener {
                onItemClick.invoke(getItem(bindingAdapterPosition))
            }
        }

        fun bind(news: News) {
            binding.newsTitle.text = news.title
            binding.newsImage.load(news.urlToImage)
        }

    }
}