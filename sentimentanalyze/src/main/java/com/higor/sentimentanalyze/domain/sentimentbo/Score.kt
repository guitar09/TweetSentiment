package com.higor.sentimentanalyze.domain.sentimentbo

internal interface Score {
    fun checkScore(score : Double) : Boolean
    fun getStatus() : SentimentStatus
}

internal enum class SentimentStatus {
    POSITIVE,
    NEUTRAL,
    NEGATIVE
}