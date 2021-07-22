package com.higor.sentimentanalyze.domain.repository

import com.higor.sentimentanalyze.domain.model.Sentiment

internal interface SentimentRepository {

    suspend fun getSentimentAnalyzeByText(text : String) : Sentiment
}