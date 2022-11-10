package com.example.socialxtest.ui.NewsFeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.socialxtest.databinding.NewsCardBinding
import com.example.socialxtest.model.Article

class NewsAdapter(
    private val inflater: LayoutInflater
) : ListAdapter<Article, NewsAdapter.ViewHolder>(DiffUtilCallback){

    object DiffUtilCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }


    class ViewHolder(private val binding : NewsCardBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(item : Article){
                    binding.article = item
                    binding.executePendingBindings()
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewsCardBinding.inflate(inflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}