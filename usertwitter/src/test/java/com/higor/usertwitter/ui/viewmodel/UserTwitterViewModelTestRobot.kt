package com.higor.usertwitter.ui.viewmodel

import com.google.common.truth.Truth.assertThat
import com.higor.core.ErrorState
import com.higor.core.SucessState
import com.higor.usertwitter.domain.usecase.TweetUseCase
import com.higor.usertwitter.ui.model.TweetUI
import com.higor.usertwitter.ui.stubs.Stubs
import io.mockk.coEvery
import io.mockk.mockk
import java.io.IOException

object UserTwitterViewModelTestRobot {

    private val useCaseUserTwitter = mockk<TweetUseCase>(relaxed = true)
    private var viewModel = UserTwitterViewModel(useCaseUserTwitter)

    class UserTwitterViewModelTestRobotArrange {

        fun mockSuccess() {
            coEvery { useCaseUserTwitter.execute("")} returns Stubs.getTweets()
        }

        fun mockError() {
            coEvery { useCaseUserTwitter.execute("")} throws IOException("ERROR")
        }

    }

    class UserTwitterViewModelTestRobotAct {
        fun fetchViewModel() {
            viewModel.fetchTweets("")
        }
    }

    class UserTwitterViewModelTestRobotAssert {
        fun checkSuccessViewModel() {
            viewModel.getTweetsLiveDate.observeForever { status ->
                assertThat(status).isInstanceOf(SucessState::class.java)

                val expected = listOf(TweetUI("123123123", "Um texto qualquer"))
                assertThat((status as SucessState<TweetUI>).data).isEqualTo(expected)
            }
        }

        fun checkErrorViewModel() {
            viewModel.getTweetsLiveDate.observeForever { status ->
                assertThat(status).isInstanceOf(ErrorState::class.java)

                val expected = "ERROR"
                assertThat((status as ErrorState<TweetUI>).error.message).isEqualTo(expected)
            }
        }
    }

    fun arrange(func : UserTwitterViewModelTestRobotArrange.() -> Unit) = UserTwitterViewModelTestRobotArrange().apply(func)
    fun act(func : UserTwitterViewModelTestRobotAct.() -> Unit) = UserTwitterViewModelTestRobotAct().apply(func)
    fun assert(func : UserTwitterViewModelTestRobotAssert.() -> Unit) = UserTwitterViewModelTestRobotAssert().apply(func)
}