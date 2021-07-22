package com.higor.usertwitter.data.datasource.api.model

import com.higor.usertwitter.data.datasource.api.model.dto.TweetErrorDTO

data class TweetResponseError(val errors: List<TweetErrorDTO>)