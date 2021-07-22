package com.higor.sentimentanalyze.ui.stubs

import com.higor.sentimentanalyze.domain.model.Sentiment

internal object Stubs {
    fun getSentimentPositive() = Sentiment("", 1.0, 1.0)
    fun getSentimentNeutral() = Sentiment("", 0.0, 1.0)
    fun getSentimentNegative() = Sentiment("", -1.0, 1.0)
}