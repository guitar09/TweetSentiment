package com.higor.sentimentanalyze.domain.usecase

import com.higor.core.usecase.AsyncUseCase
import com.higor.sentimentanalyze.domain.model.Sentiment
import com.higor.sentimentanalyze.domain.repository.SentimentRepository

internal class SentimentUseCase (val repository : SentimentRepository) :
    AsyncUseCase<Sentiment, String> {
    override suspend fun execute(params: String): Sentiment {
        return repository.getSentimentAnalyzeByText(params)
    }
}