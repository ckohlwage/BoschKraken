package com.kohlwage.boschkraken.datasources

import com.kohlwage.boschkraken.models.TradingOverviewItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface TradingListDataSource {

    val refreshState: MutableStateFlow<Boolean>

    suspend fun fetchTickerInfo(): Flow<List<TradingOverviewItem>>

    val lastRefresh: MutableStateFlow<Long>
}