package com.higor.sentimentanalyze

import android.content.Context
import com.higor.sentimentanalyze.di.SentimentKoinContext

object SentimentAnalyzeConfig {
    fun start(appContext : Context) {
        SentimentKoinContext.start(appContext)
    }
}