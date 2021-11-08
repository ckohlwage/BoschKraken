package com.kohlwage.boschkraken.models

data class TradingDetailItem(
    val tradingAsset: TradingAsset,
    val lastTradesList: List<Trade>,
)
