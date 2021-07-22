package com.higor.sentimentanalyze.data.datasource.api.model.dto

import com.google.gson.annotations.SerializedName

data class SentimentDTO(
    @SerializedName("text") val text: String,
    @SerializedName("score") val score: Double,
    @SerializedName("magnitude") val magnitude: Double
)