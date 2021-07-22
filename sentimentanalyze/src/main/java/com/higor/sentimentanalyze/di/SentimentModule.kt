package com.higor.sentimentanalyze.di

import com.higor.core.api.ClientNetwork.createHttpClient
import com.higor.core.api.ClientNetwork.createRetrofitService
import com.higor.sentimentanalyze.data.datasource.api.SentimentalAnalyzeNetworkDataSource
import com.higor.sentimentanalyze.data.datasource.api.service.GoogleService
import com.higor.sentimentanalyze.data.repositoryimpl.SentimentRepositoryImpl
import com.higor.sentimentanalyze.domain.repository.SentimentRepository
import com.higor.sentimentanalyze.ui.viewmodel.SentimentAnaylizeViewModel
import com.higor.sentimentanalyze.domain.usecase.SentimentUseCase
import com.higor.sentimentanalyze.domain.usecase.SentimentalAnalyzeUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val sentimentModule = module {

    //Data
    single { createHttpClient() }

    single {
        createRetrofitService<GoogleService>(
            okHttpClient = get(),
            baseURL = GoogleService.BASE_URL_SENTIMENTAL_ANALYZE
        )
    }


    factory {
        SentimentalAnalyzeNetworkDataSource(googleService = get())
    }

    factory {
        SentimentRepositoryImpl(sentimentalAnalyzeNetworkDataSource = get()) as SentimentRepository
    }


    //UseCases
    factory {
        SentimentUseCase(repository = get())
    }

    factory {
        SentimentalAnalyzeUseCase()
    }


    //Ui
    viewModel {
        SentimentAnaylizeViewModel(sentimentUseCase = get(), sentimentalAnalyzeUseCase = get())
    }
}