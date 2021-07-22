package com.higor.usertwitter.ui.stubs

import com.higor.usertwitter.domain.model.Tweet

internal object Stubs {

    fun getTweets() : List<Tweet> {

        return listOf(Tweet("123123123", "Um texto qualquer"))
    }
}