package com.higor.sentimentanalyze.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.higor.core.*
import com.higor.core.viewmodel.BaseViewModel
import com.higor.sentimentanalyze.ui.model.SentimentUI
import com.higor.sentimentanalyze.domain.sentimentbo.SentimentStatus
import com.higor.sentimentanalyze.domain.usecase.SentimentUseCase
import com.higor.sentimentanalyze.domain.usecase.SentimentalAnalyzeUseCase
import com.higor.sentimentanalyze.ui.mappers.toPresentationSentimentUI

internal class SentimentAnaylizeViewModel(
    val sentimentUseCase: SentimentUseCase,
    val sentimentalAnalyzeUseCase: SentimentalAnalyzeUseCase
) : BaseViewModel() {

    private val _sentimentalAnalyze = MutableLiveData<StateView<SentimentUI>>()
    val getSentimentAnalyzeLiveDate: LiveData<StateView<SentimentUI>> = _sentimentalAnalyze

    fun fetchAnalyzeText(text: String) {

        callSuspend {

            runCatching {
                _sentimentalAnalyze.value = LoadingState()
                sentimentUseCase.execute(text)

            }.onSuccess {
                _sentimentalAnalyze.value = SucessState(it.toPresentationSentimentUI())

            }.onFailure {

                _sentimentalAnalyze.value = ErrorState(it)
            }
        }
    }

      fun getSentimentalUI(score: Double): SentimentStatus {

        return sentimentalAnalyzeUseCase.execute(score)

    }

}