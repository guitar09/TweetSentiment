package com.higor.sentimentanalyze.navigation

import android.content.Context

internal interface NavigationSentiment {
    fun navigateToSentimentAnalyze(context: Context, text : String)
}