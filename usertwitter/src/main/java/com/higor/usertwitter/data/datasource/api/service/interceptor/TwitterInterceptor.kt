package com.higor.usertwitter.data.datasource.api.service.interceptor

import android.util.Log
import com.google.gson.Gson
import com.higor.usertwitter.data.datasource.api.model.TweetResponseError
import com.higor.usertwitter.data.datasource.api.service.TwitterService
import com.higor.usertwitter.domain.exception.TwitterUserException
import okhttp3.Interceptor
import okhttp3.Response


class TwitterInterceptor : Interceptor {

    val LOG = "TwitterInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        request = request?.newBuilder()
            //?.addHeader("Content-Type", "application/json")
            ?.addHeader("Authorization", TwitterService.TOKEN)
            ?.build()

        val response = chain.proceed(request)

        val responseString = response.peekBody(1024).string()
        if (responseString!!.contains("\"errors\":[")) {

            var error: TweetResponseError? = null

            try {
                error = errorConvert(responseString)
            } catch (ex: Exception) {
                Log.e(LOG, ex.toString())
            }
            error?.let {
                if (it.errors[0].resource_type == "user")
                    throw TwitterUserException()
            }

        }

        return response
    }

    fun errorConvert(errorJsonString : String) : TweetResponseError {
        return Gson().fromJson(errorJsonString, TweetResponseError::class.java)
    }
}