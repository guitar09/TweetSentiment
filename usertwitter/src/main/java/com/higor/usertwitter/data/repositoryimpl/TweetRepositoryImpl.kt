package com.higor.usertwitter.data.repositoryimpl


import com.higor.usertwitter.data.datasource.api.TwitterNetworkDataSource
import com.higor.usertwitter.data.datasource.api.mappers.toDomainTweetResponseList
import com.higor.usertwitter.domain.model.Tweet
import com.higor.usertwitter.domain.repository.TweetRepository


class TweetRepositoryImpl(private val twitterNetworkDataSource: TwitterNetworkDataSource) :
    TweetRepository {

    override suspend fun getTweetByIdUser(idUser: String): List<Tweet> {
        return twitterNetworkDataSource.getTweetById(idUser).data.toDomainTweetResponseList()
    }
}