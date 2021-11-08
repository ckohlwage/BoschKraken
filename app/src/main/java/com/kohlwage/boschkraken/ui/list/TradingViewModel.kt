package com.kohlwage.boschkraken.ui.list

import androidx.lifecycle.*
import com.kohlwage.boschkraken.datasources.TradingListDataSource
import com.kohlwage.boschkraken.util.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TradingViewModel @Inject constructor(
    private val tradingListDataSource: TradingListDataSource
) : ViewModel() {

    val refreshing = tradingListDataSource.refreshState
            .distinctUntilChanged { old, new -> old == new }
            .asLiveData(viewModelScope.coroutineContext)

    val lastUpdate = tradingListDataSource.lastRefresh
        .map { DateUtil.getTimeFromMillies(it) }
        .asLiveData(viewModelScope.coroutineContext)

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean>
        get() = _error

    suspend fun getTradingListFlow() =
        tradingListDataSource.fetchTickerInfo()
            .flowOn(Dispatchers.IO)
            .onEach { _error.postValue(false) }
            .catch { _error.postValue(true) }

}