package com.higor.usertwitter

import com.higor.core.api.ClientNetwork.createHttpClient
import com.higor.core.api.ClientNetwork.createRetrofitService
import com.higor.usertwitter.data.datasource.api.TwitterNetworkDataSource
import com.higor.usertwitter.data.datasource.api.service.TwitterService
import com.higor.usertwitter.data.datasource.api.service.interceptor.TwitterInterceptor
import com.higor.usertwitter.data.repositoryimpl.TweetRepositoryImpl
import com.higor.usertwitter.domain.repository.TweetRepository
import com.higor.usertwitter.domain.usecase.TweetUseCase
import com.higor.usertwitter.ui.viewmodel.UserTwitterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val userTwitterModule = module {

    //Data
    single {
        createRetrofitService<TwitterService>(
            okHttpClient = createHttpClient(listOf(TwitterInterceptor())),
            baseURL = TwitterService.BASE_URL_TWITTER
        )
    }

    factory {
        TwitterNetworkDataSource(twitterService = get())
    }

    factory {
        TweetRepositoryImpl(twitterNetworkDataSource = get()) as TweetRepository
    }

    //UseCases
    factory {
        TweetUseCase(repository = get())
    }


    //UI
    viewModel {
        UserTwitterViewModel(tweetUseCase = get())
    }




}