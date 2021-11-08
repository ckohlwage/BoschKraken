package com.kohlwage.boschkraken.repositories

import com.google.gson.JsonObject
import com.kohlwage.boschkraken.datasources.TradingDetailDataSource
import com.kohlwage.boschkraken.models.Trade
import com.kohlwage.boschkraken.models.TradingDetailItem
import com.kohlwage.boschkraken.models.responses.TradingAssetResponse
import com.kohlwage.boschkraken.models.responses.asTradingAsset
import com.kohlwage.boschkraken.network.service.KrakenService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TradingDetailRepository @Inject constructor(
    private val krakenService: KrakenService
) : TradingDetailDataSource {

    val RELOAD_DELAY = 60000L

    override val lastRefresh: MutableStateFlow<Long> = MutableStateFlow(0L)

    override val refreshState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override suspend fun fetchAssetDetail(assetId: String): Flow<TradingDetailItem> = flow {
        while (true) {
            refreshState.emit(true)
            val assetInfo = krakenService.fetchAssetPairs(assetId)
            val tradeInfo = krakenService.fetchLastTrades(assetId)
            if (assetInfo.error.isEmpty() && tradeInfo.error.isEmpty()) {
                emit(Pair(assetInfo.result, tradeInfo.result))
                refreshState.emit(false)
                lastRefresh.emit(System.currentTimeMillis())
            } else error("Loading error")
            delay(RELOAD_DELAY)
        }
    }.flatMapMerge {
        combineAssetAndTicker(assetId, it)
    }

    private suspend fun combineAssetAndTicker(
        assetId: String,
        resultPair: Pair<Map<String, TradingAssetResponse>, JsonObject>
    ): Flow<TradingDetailItem> = flow {
        val asset = resultPair.first[assetId]?.asTradingAsset(assetId) ?: error("loading failed")
        val trades = getTradesList(resultPair.second, assetId)

        val detailItem = TradingDetailItem(
            tradingAsset = asset,
            lastTradesList = trades,
        )
        emit(detailItem)
    }

    private fun getTradesList(trades: JsonObject, assetId: String): List<Trade> {
        val array = trades.getAsJsonArray(assetId).map { it.asJsonArray }
        return array.take(3).map {
            Trade(it[0].asString, it[1].asString, it[2].asLong)
        }
    }

}