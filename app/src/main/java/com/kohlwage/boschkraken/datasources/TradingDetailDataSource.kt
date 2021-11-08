package com.kohlwage.boschkraken.datasources

import com.kohlwage.boschkraken.models.TradingDetailItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface TradingDetailDataSource {

    val lastRefresh: MutableStateFlow<Long>

    val refreshState: MutableStateFlow<Boolean>

    suspend fun fetchAssetDetail(assetId: String): Flow<TradingDetailItem>
}