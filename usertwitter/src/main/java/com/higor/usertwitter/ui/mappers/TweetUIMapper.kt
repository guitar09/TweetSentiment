package com.higor.usertwitter.ui.mappers

import com.higor.usertwitter.domain.model.Tweet
import com.higor.usertwitter.ui.model.TweetUI


internal fun List<Tweet>.toPresentationTweetUIList() = map {
    TweetUI(id = it.id, text = it.text)
}