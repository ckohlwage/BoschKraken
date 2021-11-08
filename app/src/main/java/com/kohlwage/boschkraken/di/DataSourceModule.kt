package com.kohlwage.boschkraken.di

import com.kohlwage.boschkraken.datasources.TradingDetailDataSource
import com.kohlwage.boschkraken.datasources.TradingListDataSource
import com.kohlwage.boschkraken.repositories.TradingDetailRepository
import com.kohlwage.boschkraken.repositories.TradingListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun TradingListDataSource(tradingListRepository: TradingListRepository): TradingListDataSource

    @Binds
    @Singleton
    fun TradingDetailDataSource(tradingDetailRepository: TradingDetailRepository): TradingDetailDataSource
}