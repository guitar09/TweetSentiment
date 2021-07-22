package com.higor.sentimentanalyze.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.higor.sentimentanalyze.R

internal class SentimentAnalyzeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr) {

    private val tvSentimentEmoji: TextView by lazy { findViewById(R.id.tvSentimentEmoji) }
    private val tvSentimenTitle: TextView by lazy { findViewById(R.id.tvSentimenTitle) }

    init {
        inflate(context, R.layout.sentimental_view, this)
    }

    fun bind(model : Model) {
        tvSentimentEmoji.text = model.emoji
        tvSentimenTitle.text = model.text
    }


    data class Model(val emoji : String, val text : String)
}