package com.kohlwage.boschkraken.network.service

import com.google.gson.JsonObject
import com.kohlwage.boschkraken.models.responses.*
import retrofit2.http.GET
import retrofit2.http.Query

interface KrakenService {

    @GET("public/AssetPairs")
    suspend fun fetchAssetPairs(
        @Query("pair") pair: String?,
        @Query("info") info: String = "info",
    ): KrakenResponse<Map<String, TradingAssetResponse>>

    @GET("public/Ticker")
    suspend fun fetchTicker(
        @Query("pair") tradingPairs: String,
    ): KrakenResponse<Map<String,TickerResponse>>

    @GET("public/Trades")
    suspend fun fetchLastTrades(
        @Query("pair") tradingPair: String,
    ): KrakenResponse<JsonObject>

}