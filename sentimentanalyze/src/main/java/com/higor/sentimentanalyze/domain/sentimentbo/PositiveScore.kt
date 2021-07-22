package com.higor.sentimentanalyze.domain.sentimentbo

internal class PositiveScore : Score {
    override fun checkScore(score: Double): Boolean {
        return (score >= 0.25)
    }

    override fun getStatus(): SentimentStatus {
        return SentimentStatus.POSITIVE
    }
}