package com.higor.sentimentanalyze.data.repositoryimpl

import com.higor.sentimentanalyze.data.datasource.api.SentimentalAnalyzeNetworkDataSource
import com.higor.sentimentanalyze.data.datasource.api.mappers.toDomainSentimentResponse
import com.higor.sentimentanalyze.domain.model.Sentiment
import com.higor.sentimentanalyze.domain.repository.SentimentRepository

class SentimentRepositoryImpl(private val sentimentalAnalyzeNetworkDataSource: SentimentalAnalyzeNetworkDataSource) :
    SentimentRepository {

    override suspend fun getSentimentAnalyzeByText(text: String): Sentiment {
        return sentimentalAnalyzeNetworkDataSource.getSentimentAnalyzeByText(text).data.toDomainSentimentResponse()
    }
}