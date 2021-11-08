package com.kohlwage.boschkraken.repositories

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kohlwage.boschkraken.models.responses.KrakenResponse
import com.kohlwage.boschkraken.models.responses.TradingAssetResponse
import com.kohlwage.boschkraken.network.service.KrakenService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import androidx.paging.PagingSource.*
import com.kohlwage.boschkraken.models.TradingOverviewItem
import com.kohlwage.boschkraken.models.responses.TickerResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Ignore
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


class TradingListRepositoryTest {

    private val service: KrakenService = mock()

    private lateinit var repository: TradingListRepository

    @Before
    fun setUp() {
        repository = TradingListRepository(service)
    }

    @Test
    @Ignore
    fun `GIVEN call successful WHEN fetchTickerInfo THEN return LoadResult Page`() {
        runBlocking {
            whenever(service.fetchAssetPairs(any(), any())).thenReturn(mockAssetList)
            whenever(service.fetchTicker(any())).thenReturn(KrakenResponse(error = emptyArray(),
            result = mapOf()))

            val result = repository.fetchTickerInfo()

            assertEquals(null, result)
        }
    }

    @Test
    @Ignore
    fun `GIVEN call error WHEN fetchMovieDetails THEN return LoadResult Error`() {
        runBlocking {

        }
    }

    val mockAssetListJson = "{\"error\":[],\"result\":" +
            "{\"1INCHEUR\":{\"altname\":\"1INCHEUR\",\"wsname\":\"1INCH/EUR\",\"aclass_base\":\"currency\"," +
            "\"base\":\"1INCH\",\"aclass_quote\":\"currency\",\"quote\":\"ZEUR\",\"lot\":\"unit\",\"pair_decimals\":3," +
            "\"lot_decimals\":8,\"lot_multiplier\":1, \"fee_volume_currency\":\"ZUSD\",\"margin_call\":80,\"margin_stop\":40,\"ordermin\":\"2\"}," +
            "\"1INCHUSD\":{\"altname\":\"1INCHUSD\",\"wsname\":\"1INCH/USD\",\"aclass_base\":\"currency\"," +
            "\"base\":\"1INCH\",\"aclass_quote\":\"currency\",\"quote\":\"ZUSD\",\"lot\":\"unit\",\"pair_decimals\":3," +
            "\"lot_decimals\":8,\"lot_multiplier\":1,\"fee_volume_currency\":\"ZUSD\",\"margin_call\":80,\"margin_stop\":40,\"ordermin\":\"2\"}}}"
    val mockAssetList: KrakenResponse<Map<String, TradingAssetResponse>> = Gson().fromJson(mockAssetListJson, TestUtils.getKrakenResponseWithMap(
        object: TypeToken<TradingAssetResponse>() {}.type
    ))
    val mockTickerListJson = "{\"error\":[],\"result\":{\"1INCHEUR\":{\"a\":[\"3.91900\",\"1148\",\"1148.000\"],\"b\":[\"3.91400\",\"773\",\"773.000\"],\"c\":[\"3.91900\",\"4.34962926\"],\"v\":[\"29043.55998437\",\"64569.62009992\"],\"p\":[\"3.85755\",\"3.82436\"],\"t\":[180,280],\"l\":[\"3.78200\",\"3.73000\"],\"h\":[\"3.99800\",\"3.99800\"],\"o\":\"3.81300\"}}"
    val mockTickerList: KrakenResponse<Map<String, TickerResponse>> = Gson().fromJson(mockTickerListJson, TestUtils.getKrakenResponseWithMap(
        object: TypeToken<TickerResponse>() {}.type
    ))

}