package com.higor.core.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ClientNetwork {

    fun createLoggin() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    fun createHttpClient(interceptors: List<Interceptor>? = null) : OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        interceptors?.let {
            for(logging in interceptors) {
                okHttpClient.addInterceptor(logging)
            }
        }

        return okHttpClient.build()
    }


    inline fun <reified T> createRetrofitService(okHttpClient: OkHttpClient, baseURL : String): T =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            //.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(baseURL)
            .build()
            .create(T::class.java)

}