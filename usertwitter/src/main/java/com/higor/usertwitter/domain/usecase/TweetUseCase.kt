package com.higor.usertwitter.domain.usecase

import com.higor.core.usecase.AsyncUseCase
import com.higor.usertwitter.domain.model.Tweet
import com.higor.usertwitter.domain.repository.TweetRepository

internal class TweetUseCase(val repository : TweetRepository) : AsyncUseCase<List<Tweet>, String> {
    override suspend fun execute(params: String): List<Tweet> {
        return repository.getTweetByIdUser(params)
    }
}