package com.kohlwage.boschkraken.models

data class TradingAsset(
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
    val volumeCurrency: String,
    val marginCall: Int,
    val marginStop: Int,
    val orderMin: String,
)

