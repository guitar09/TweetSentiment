package com.higor.sentimentanalyze.domain.sentimentbo

internal class NeutralScore : Score {
    override fun checkScore(score: Double): Boolean {
        return (score >= -0.25 && score < 0.25)
    }

    override fun getStatus(): SentimentStatus {
        return SentimentStatus.NEUTRAL
    }
}