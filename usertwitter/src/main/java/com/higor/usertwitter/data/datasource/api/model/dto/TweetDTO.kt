package com.higor.usertwitter.data.datasource.api.model.dto

import com.google.gson.annotations.SerializedName

data class TweetDTO(
    @SerializedName("id") val id: String,
    @SerializedName("text") val text: String
)