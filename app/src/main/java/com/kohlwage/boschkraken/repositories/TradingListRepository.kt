package com.kohlwage.boschkraken.repositories

import com.kohlwage.boschkraken.datasources.TradingListDataSource
import com.kohlwage.boschkraken.models.TradingOverviewItem
import com.kohlwage.boschkraken.models.responses.TickerResponse
import com.kohlwage.boschkraken.models.responses.TradingAssetResponse
import com.kohlwage.boschkraken.network.service.KrakenService
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TradingListRepository @Inject constructor(
    private val service: KrakenService
) : TradingListDataSource {

    val RELOAD_DELAY = 60000L

    override val lastRefresh: MutableStateFlow<Long> = MutableStateFlow(0L)

    override val refreshState: MutableStateFlow<Boolean> = MutableStateFlow(false)

    @FlowPreview
    override suspend fun fetchTickerInfo(): Flow<List<TradingOverviewItem>> = flow {
        while (true) {
            refreshState.emit(true)
            val assets = service.fetchAssetPairs(null)
            if (assets.error.isEmpty()) {
                val assetsMap = assets.result
                val tickerMap = loadTickerInfo(assetsMap.keys.toMutableList())
                emit(Pair(assetsMap, tickerMap))
                refreshState.emit(false)
                lastRefresh.emit(System.currentTimeMillis())
            } else error("Loading Error")
            delay(RELOAD_DELAY)
        }
    }.flatMapMerge {
        combineAssetAndTicker(it)
    }

    private suspend fun loadTickerInfo(
        keys: MutableList<String>,
        resultMap: HashMap<String, TickerResponse>? = HashMap()
    ): Map<String, TickerResponse> {
        while (keys.size > 0) {
            val array = keys.take(200).toTypedArray()
            val result = service.fetchTicker(array.joinToString(",")).result
            resultMap?.putAll(result)
            keys.removeAll(array)
        }
        return resultMap ?: HashMap()
    }

    private suspend fun combineAssetAndTicker(resultPair: Pair<Map<String, TradingAssetResponse>, Map<String, TickerResponse>>)
            : Flow<List<TradingOverviewItem>> = flow {
        val transformedList = resultPair.first.keys.map { key ->
                TradingOverviewItem(
                    key,
                    resultPair.first[key],
                    resultPair.second[key]
                )
        }
        emit(transformedList)
    }

}