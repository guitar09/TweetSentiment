package com.higor.sentimentanalyze.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.dsl.koinApplication

internal object SentimentKoinContext {
    lateinit var koinApp: KoinApplication

    fun start(appContext: Context) {
        koinApp = koinApplication {
            androidContext(appContext)
            modules(sentimentModule)
        }
    }
}