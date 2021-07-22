package com.higor.usertwitter.domain.repository

import com.higor.usertwitter.domain.model.Tweet

internal interface TweetRepository {
    suspend fun getTweetByIdUser(idUser : String) : List<Tweet>
}