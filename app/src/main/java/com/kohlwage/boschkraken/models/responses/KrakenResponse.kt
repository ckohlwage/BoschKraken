package com.kohlwage.boschkraken.models.responses

import com.google.gson.annotations.SerializedName

data class KrakenResponse<T>(
    @SerializedName("result") val result: T,
    @SerializedName("error") val error: Array<String>,
)
