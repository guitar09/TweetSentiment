package com.higor.sentimentanalyze.domain

import com.google.common.truth.Truth
import com.higor.sentimentanalyze.domain.exception.SentimentNotFoundException
import com.higor.sentimentanalyze.domain.sentimentbo.NegativeScore
import com.higor.sentimentanalyze.domain.sentimentbo.NeutralScore
import com.higor.sentimentanalyze.domain.sentimentbo.PositiveScore
import com.higor.sentimentanalyze.domain.sentimentbo.SentimentStatus
import org.junit.Assert.assertThrows
import org.junit.Test

class SentimentScoreTest {

    @Test
    fun `When not exists sentiment, should return error`() {
        val analyze = SentimentScore(listOf(NeutralScore(), NegativeScore()), 1.0)

        val thrown = assertThrows(SentimentNotFoundException::class.java) { analyze.getSentimental() }
        val exptecedMessage = "Não foi possível detectar o sentimento"

        Truth.assertThat(thrown).isInstanceOf(SentimentNotFoundException::class.java)
        Truth.assertThat(thrown.message).contains(exptecedMessage)
    }

    @Test
    fun `When sentiment is positive, should return positive status`() {
        val analyze = SentimentScore(listOf(PositiveScore(), NeutralScore(), NegativeScore()), 1.0)
        Truth.assertThat(analyze.getSentimental()).isEqualTo(SentimentStatus.POSITIVE)
    }

    @Test
    fun `When sentiment is neutral, should return neutral status`() {
        val analyze = SentimentScore(listOf(PositiveScore(), NeutralScore(), NegativeScore()), 0.24)
        Truth.assertThat(analyze.getSentimental()).isEqualTo(SentimentStatus.NEUTRAL)
    }

    @Test
    fun `When sentiment is negative, should return negative status`() {
        val analyze = SentimentScore(listOf(PositiveScore(), NeutralScore(), NegativeScore()), -1.0)
        Truth.assertThat(analyze.getSentimental()).isEqualTo(SentimentStatus.NEGATIVE)
    }

}