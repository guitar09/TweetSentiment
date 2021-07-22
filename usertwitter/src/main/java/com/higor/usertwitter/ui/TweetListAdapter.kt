package com.higor.usertwitter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.higor.usertwitter.R
import com.higor.usertwitter.ui.customview.TweetView
import com.higor.usertwitter.ui.model.TweetUI

internal class TweetListAdapter(private val onClick: OnClickItemTweetAdapter) :
    ListAdapter<TweetUI, TweetListAdapter.TweetViewHolder>(TweetListAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        return TweetViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class TweetViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var row = itemView.findViewById<TweetView>(R.id.tweetViewRow)

        fun bind(data: TweetUI, onClick: OnClickItemTweetAdapter) {
            with(itemView) {
                row.bind(TweetView.Model(text = data.text))
                row.setOnClickListener { onClick.onClickItem(data) }
            }
        }

        companion object {
            fun from(parent: ViewGroup): TweetViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.tweet_view_row_layout, parent, false)
                return TweetViewHolder(view)
            }
        }
    }

    interface OnClickItemTweetAdapter {
        fun onClickItem(tweet : TweetUI)
    }

    private companion object : DiffUtil.ItemCallback<TweetUI>() {

        override fun areItemsTheSame(oldItem: TweetUI, newItem: TweetUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TweetUI, newItem: TweetUI): Boolean {
            return oldItem == newItem
        }
    }
}