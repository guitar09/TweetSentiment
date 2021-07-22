package com.higor.sentimentanalyze.data.datasource.api.service

import com.higor.sentimentanalyze.data.datasource.api.model.SentimentReponse
import com.higor.sentimentanalyze.data.datasource.api.model.SentimentRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleService {


    @POST(OPKEY_GET_SENTIMENT_ANALYZE)
    suspend fun getSentimentAnalyzeByText(@Body description : SentimentRequest) : SentimentReponse

    companion object {
        const val BASE_URL_SENTIMENTAL_ANALYZE = "https://us-central1-tweetsentimental.cloudfunctions.net/api/"
        const val OPKEY_GET_SENTIMENT_ANALYZE = "analyze"
    }

}