package com.higor.usertwitter.data.datasource.api.service

import com.higor.usertwitter.data.datasource.api.model.TweetResponse
import com.higor.usertwitter.data.datasource.api.model.UserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TwitterService {

    @GET(OPKEY_GET_ID_USER)
    suspend fun geIdByUser(@Path("username") userName : String/*, @Header("Authorization") authorization : String = TOKEN, */) : UserResponse
    @GET(OPKEY_GET_TWEETS_USERS)
    suspend fun getTweetById(@Path("id") id : String/*, @Header("Authorization") authorization : String = TOKEN,*/) : TweetResponse

    companion object {
        const val BASE_URL_TWITTER = "https://api.twitter.com/2/"
        const val OPKEY_GET_ID_USER = "users/by/username/{username}"
        const val OPKEY_GET_TWEETS_USERS = "users/{id}/tweets"

        const val TOKEN = "Bearer AAAAAAAAAAAAAAAAAAAAAGhxRgEAAAAAHjwOrKRm9kzdW1nlF09g62Uau7Y%3DsLxxm9uvgS5BwP7gdYwNwtfy782vJBONaiTUl92rXCrF9eyOo1"
    }
}

