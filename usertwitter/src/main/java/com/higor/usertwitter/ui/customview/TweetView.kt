package com.higor.usertwitter.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.higor.usertwitter.R

internal class TweetView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    private val tvTitleTweetViewRow: TextView by lazy { findViewById(R.id.tvTitleTweetViewRow) }

    init {
        inflate(context, R.layout.user_tweet_row, this)
    }

    fun bind(model: Model) {

        tvTitleTweetViewRow.text = model.text
    }

    data class Model(val text: String)
}