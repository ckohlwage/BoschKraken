package com.kohlwage.boschkraken.models.responses

import com.google.gson.annotations.SerializedName
import com.kohlwage.boschkraken.models.TradingAsset

data class TradingAssetResponse(
    @SerializedName("altname") val altname: String,
    @SerializedName("wsname") val wsname: String,
    @SerializedName("aclass_base") val aclassBase: String,
    @SerializedName("base") val base: String,
    @SerializedName("aclass_quote") val aclassQuote: String,
    @SerializedName("quote") val quote: String,
    @SerializedName("lot") val lot: String,
    @SerializedName("pair_decimals") val pairDecimals: Int,
    @SerializedName("lot_decimals") val lotDecimals: Int,
    @SerializedName("lot_multiplier") val lotMultiplier: Int,
    @SerializedName("fee_volume_currency") val volumeCurrency: String,
    @SerializedName("margin_call") val marginCall: Int,
    @SerializedName("margin_stop") val marginStop: Int,
    @SerializedName("ordermin") val orderMin: String,
)

fun TradingAssetResponse.asTradingAsset(assetName: String) = TradingAsset(
    name = assetName,
    altname = altname,
    wsname = wsname,
    aclassBase = aclassBase,
    base = base,
    aclassQuote = aclassQuote,
    quote = quote,
    lot = lot,
    pairDecimals = pairDecimals,
    lotDecimals = lotDecimals,
    lotMultiplier = lotMultiplier,
    volumeCurrency = volumeCurrency,
    marginCall = marginCall,
    marginStop = marginStop,
    orderMin = orderMin,
)

