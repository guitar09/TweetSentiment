package com.higor.sentimentanalyze.data.datasource.api.model

import com.google.gson.annotations.SerializedName

class SentimentRequest(@SerializedName("description") val description : String)