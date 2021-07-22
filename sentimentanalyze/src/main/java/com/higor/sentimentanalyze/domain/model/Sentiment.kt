package com.higor.sentimentanalyze.domain.model

 data class Sentiment(
    val text: String,
    val score: Double,
    val magnitude: Double
)