package com.kohlwage.boschkraken.repositories

import com.kohlwage.boschkraken.network.service.KrakenService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock

class TradingDetailRepositoryTest {

    private val service: KrakenService = mock()

    private lateinit var repository: TradingDetailRepository

    @Before
    fun setUp() {
        repository = TradingDetailRepository(service)
    }

    @Test
    fun `GIVEN call successful WHEN fetchMovieDetails THEN return movie`() {
        runBlocking {

        }
    }

    @Test
    fun `GIVEN call error WHEN fetchMovieDetails THEN return null`() {
        runBlocking {

        }
    }


}