package com.higor.usertwitter

import android.content.Context
import com.higor.sentimentanalyze.SentimentAnalyzeConfig
import com.higor.usertwitter.di.UserTwitterKoinContext

object UserTwitterConfig {

    fun start(appContext : Context) {
        SentimentAnalyzeConfig.start(appContext)
        UserTwitterKoinContext.start(appContext)
    }
}