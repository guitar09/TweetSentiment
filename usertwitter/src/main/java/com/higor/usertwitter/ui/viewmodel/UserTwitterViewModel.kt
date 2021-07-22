package com.higor.usertwitter.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.higor.core.ErrorState
import com.higor.core.LoadingState
import com.higor.core.StateView
import com.higor.core.SucessState
import com.higor.core.viewmodel.BaseViewModel
import com.higor.usertwitter.domain.usecase.TweetUseCase
import com.higor.usertwitter.ui.mappers.toPresentationTweetUIList
import com.higor.usertwitter.ui.model.TweetUI

internal class UserTwitterViewModel(private val tweetUseCase: TweetUseCase) : BaseViewModel() {

    private val _listTweets = MutableLiveData<StateView<List<TweetUI>>>()
    val getTweetsLiveDate: LiveData<StateView<List<TweetUI>>> = _listTweets

    fun fetchTweets(userName : String) {

        callSuspend {

            runCatching {
                _listTweets.value = LoadingState()
                tweetUseCase.execute(userName)

            }.onSuccess {
               _listTweets.value = SucessState(it.toPresentationTweetUIList())

            }.onFailure {

              _listTweets.value = ErrorState(it)
            }
        }
    }
}