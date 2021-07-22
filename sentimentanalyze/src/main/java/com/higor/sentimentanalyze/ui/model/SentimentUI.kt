package com.higor.sentimentanalyze.ui.model

data class SentimentUI(
    val text: String,
    val score: Double,
    val magnitude: Double
)