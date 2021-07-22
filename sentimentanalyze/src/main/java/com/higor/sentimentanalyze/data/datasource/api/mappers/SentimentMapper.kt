package com.higor.sentimentanalyze.data.datasource.api.mappers

import com.higor.sentimentanalyze.data.datasource.api.model.dto.SentimentDTO
import com.higor.sentimentanalyze.domain.model.Sentiment


fun SentimentDTO.toDomainSentimentResponse() = Sentiment(text = this.text, score = this.score, magnitude = this.magnitude)