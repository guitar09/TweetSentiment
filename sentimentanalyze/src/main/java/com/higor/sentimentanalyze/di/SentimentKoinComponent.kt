package com.higor.sentimentanalyze.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

internal interface SentimentKoinComponent : KoinComponent {
    override fun getKoin(): Koin {
        return SentimentKoinContext.koinApp.koin
    }
}