package com.higor.sentimentanalyze.ui.mappers

import com.higor.sentimentanalyze.domain.model.Sentiment
import com.higor.sentimentanalyze.ui.model.SentimentUI

internal fun Sentiment.toPresentationSentimentUI() = SentimentUI(text = this.text, score = this.score, magnitude = this.magnitude)