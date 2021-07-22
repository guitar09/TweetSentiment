package com.higor.sentimentanalyze.domain

import com.higor.sentimentanalyze.domain.Constants
import com.higor.sentimentanalyze.domain.exception.SentimentNotFoundException
import com.higor.sentimentanalyze.domain.sentimentbo.Score
import com.higor.sentimentanalyze.domain.sentimentbo.SentimentStatus
import kotlin.jvm.Throws

internal data class SentimentScore(private val listScore : List<Score>, val score : Double) {

    @Throws(SentimentNotFoundException::class)
    fun getSentimental() : SentimentStatus {

      for(validation in listScore) {
          if(validation.checkScore(score)) return validation.getStatus()
      }

        throw SentimentNotFoundException(Constants.SENTIMENT_NOT_FOUND)

    }
}