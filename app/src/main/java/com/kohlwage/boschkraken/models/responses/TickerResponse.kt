package com.kohlwage.boschkraken.models.responses

import com.google.gson.annotations.SerializedName

data class TickerResponse(
    @SerializedName("v") val volume: Array<String>,
    @SerializedName("c") val lastTrade: Array<String>,
)
