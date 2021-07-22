package com.higor.usertwitter.data.datasource.api

import com.higor.usertwitter.data.datasource.api.exception.networkHandlerExceptions
import com.higor.usertwitter.data.datasource.api.model.TweetResponse
import com.higor.usertwitter.data.datasource.api.service.TwitterService
import kotlin.jvm.Throws

class TwitterNetworkDataSource(private val twitterService: TwitterService) {

    @Throws(Exception::class)
    suspend fun getTweetById(userName: String): TweetResponse {
        return try {
            val idUser = twitterService.geIdByUser(userName)
            twitterService.getTweetById(idUser.data.id)
        } catch (ex: Exception) {
            throw networkHandlerExceptions(ex)
        }
    }
}