package com.higor.sentimentanalyze.data.datasource.api

import com.higor.sentimentanalyze.data.datasource.api.exception.networkHandlerExceptions
import com.higor.sentimentanalyze.data.datasource.api.service.GoogleService
import com.higor.sentimentanalyze.data.datasource.api.model.SentimentReponse
import com.higor.sentimentanalyze.data.datasource.api.model.SentimentRequest
import kotlin.jvm.Throws


class SentimentalAnalyzeNetworkDataSource(private val googleService: GoogleService) {

    @Throws(Exception::class)
    suspend fun getSentimentAnalyzeByText(text: String): SentimentReponse {
        return try {
            googleService.getSentimentAnalyzeByText(SentimentRequest(text))
        }catch (ex : Exception) {
            throw networkHandlerExceptions(ex)
        }
    }
}