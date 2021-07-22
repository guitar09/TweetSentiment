package com.higor.sentimentanalyze.domain.usecase

import com.higor.core.usecase.UseCase
import com.higor.sentimentanalyze.domain.SentimentScore
import com.higor.sentimentanalyze.domain.sentimentbo.NegativeScore
import com.higor.sentimentanalyze.domain.sentimentbo.NeutralScore
import com.higor.sentimentanalyze.domain.sentimentbo.PositiveScore
import com.higor.sentimentanalyze.domain.sentimentbo.SentimentStatus

internal class SentimentalAnalyzeUseCase : UseCase<SentimentStatus, Double> {

    override fun execute(score : Double) : SentimentStatus {
        val analyze = SentimentScore(listOf(PositiveScore(), NeutralScore(), NegativeScore()), score)
        return  analyze.getSentimental()
    }
}