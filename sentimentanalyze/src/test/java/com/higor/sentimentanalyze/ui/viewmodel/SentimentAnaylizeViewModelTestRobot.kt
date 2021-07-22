package com.higor.sentimentanalyze.ui.viewmodel

import com.google.common.truth.Truth.assertThat
import com.higor.core.ErrorState
import com.higor.core.SucessState
import com.higor.sentimentanalyze.domain.model.Sentiment
import com.higor.sentimentanalyze.ui.model.SentimentUI
import com.higor.sentimentanalyze.ui.stubs.Stubs
import com.higor.sentimentanalyze.domain.usecase.SentimentUseCase
import com.higor.sentimentanalyze.domain.usecase.SentimentalAnalyzeUseCase
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException

internal object SentimentAnaylizeViewModelTestRobot {

    private val useCaseSentimet = mockk<SentimentUseCase>(relaxed = true)
    private val useCaseAnalyse = SentimentalAnalyzeUseCase()
    private var viewModel = SentimentAnaylizeViewModel(useCaseSentimet, useCaseAnalyse)

    class SentimentAnaylizeViewModelTestRobotArrange {

        fun mockSuccess() {
            coEvery { useCaseSentimet.execute("")} returns Stubs.getSentimentPositive()
        }

        fun mockError() {
            coEvery { useCaseSentimet.execute("")} throws IOException("ERROR")
        }

    }

    class SentimentAnaylizeViewModelTestRobotAct {

        fun fetchViewModel() {
            viewModel.fetchAnalyzeText("")
        }

    }

    class SentimentAnaylizeViewModelTestRobotAssert {

        fun checkSuccessViewModel() {
            viewModel.getSentimentAnalyzeLiveDate.observeForever { status ->
                assertThat(status).isInstanceOf(SucessState::class.java)

                val expected = SentimentUI("", 1.0, 1.0)
                assertThat((status as SucessState<Sentiment>).data).isEqualTo(expected)
            }
        }

        fun checkErrorViewModel() {
            viewModel.getSentimentAnalyzeLiveDate.observeForever { status ->
                assertThat(status).isInstanceOf(ErrorState::class.java)

                val expected = "ERROR"
                assertThat((status as ErrorState<Sentiment>).error.message).isEqualTo(expected)
            }
        }
    }

    fun arrange(func : SentimentAnaylizeViewModelTestRobotArrange.() -> Unit) = SentimentAnaylizeViewModelTestRobotArrange().apply(func)
    fun act(func : SentimentAnaylizeViewModelTestRobotAct.() -> Unit) = SentimentAnaylizeViewModelTestRobotAct().apply(func)
    fun assert(func : SentimentAnaylizeViewModelTestRobotAssert.() -> Unit) = SentimentAnaylizeViewModelTestRobotAssert().apply(func)

}