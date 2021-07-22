package com.higor.usertwitter.data.datasource.api.mappers


import com.higor.usertwitter.data.datasource.api.model.dto.ListTweetResponseBodyDTO
import com.higor.usertwitter.domain.model.Tweet

fun ListTweetResponseBodyDTO.toDomainTweetResponseList() =
    map {
        Tweet(
            id = it.id,
            text = it.text
        )

    }