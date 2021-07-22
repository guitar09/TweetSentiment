package com.higor.sentimentanalyze.navigation

import android.content.Context
import com.higor.sentimentanalyze.ui.SentimentAnalyzeActivity

class NavigationSentimentRoute : NavigationSentiment {
    override fun navigateToSentimentAnalyze(context: Context, text: String) {
        context.startActivity(SentimentAnalyzeActivity.callThisActivity(context, text))
    }
}