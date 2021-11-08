package com.kohlwage.boschkraken.ui.details

import androidx.lifecycle.*
import com.kohlwage.boschkraken.datasources.TradingDetailDataSource
import com.kohlwage.boschkraken.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class TradingAssetDetailsViewModel @Inject constructor(
    private val detailDataSource: TradingDetailDataSource,
) : ViewModel() {

    var assetId: String = ""

    val refreshing = detailDataSource.refreshState
        .distinctUntilChanged { old, new -> old == new }
        .asLiveData(viewModelScope.coroutineContext)

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean>
        get() = _error

    val lastUpdate: LiveData<String> =
        detailDataSource.lastRefresh
            .map { DateUtil.getTimeFromMillies(it) }
            .asLiveData()

    suspend fun getDetailFlow() = detailDataSource.fetchAssetDetail(assetId)
        .flowOn(Dispatchers.IO)
        .onEach { _error.postValue(false) }
        .catch {
            _error.postValue(true)
        }

}