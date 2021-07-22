package com.higor.sentimentanalyze.domain.sentimentbo

internal class NegativeScore : Score {
    override fun checkScore(score: Double): Boolean {
        return score < -0.25
    }

    override fun getStatus(): SentimentStatus {
        return SentimentStatus.NEGATIVE
    }
}