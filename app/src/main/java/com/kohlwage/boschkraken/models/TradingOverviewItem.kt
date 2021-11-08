package com.kohlwage.boschkraken.models

import com.kohlwage.boschkraken.models.responses.TickerResponse
import com.kohlwage.boschkraken.models.responses.TradingAssetResponse

data class TradingOverviewItem(
    val name: String,
    val altname: String,
    val wsname: String,
    val aclassBase: String,
    val base: String,
    val aclassQuote: String,
    val quote: String,
    val lot: String,
    val pairDecimals: Int,
    val lotDecimals: Int,
    val lotMultiplier: Int,
    val volume: String,
    val volume24: String,
    val lastTradePrice: String,
    val lastTradeVolume: String,
) {
    constructor(key: String, asset: TradingAssetResponse?, tickerInfo: TickerResponse?): this(
        name = key,
        altname = asset?.altname ?:"",
        wsname = asset?.wsname ?:"",
        aclassBase = asset?.aclassBase ?:"",
        base = asset?.base ?:"",
        aclassQuote = asset?.aclassQuote ?:"",
        quote = asset?.quote ?: "",
        lot = asset?.lot ?:"",
        pairDecimals = asset?.pairDecimals ?:2,
        lotDecimals = asset?.lotDecimals ?: 2,
        lotMultiplier = asset?.lotMultiplier ?:2,
        volume = tickerInfo?.volume?.get(0) ?: "NA",
        volume24 = tickerInfo?.volume?.get(1) ?: "NA",
        lastTradePrice = tickerInfo?.lastTrade?.get(0) ?: "NA",
        lastTradeVolume = tickerInfo?.lastTrade?.get(1) ?: "NA"
    )
}
