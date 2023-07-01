package com.example.data.repository

import com.example.data.datasource.remote.CurrencyApiService
import com.example.data.models.CurrencyDataDto
import com.example.data.models.QuotesDto
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
internal class CurrencyRepositoryImplTest {

    @Mock
    private lateinit var currencyRepositoryImpl: CurrencyRepositoryImpl

    @Mock
    lateinit var apiService: CurrencyApiService

    @Before
    fun setUp() {
        currencyRepositoryImpl = CurrencyRepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetCurrencyFromServer() = runBlocking {

        val accessKey = "843d4d34ae72b3882e3db642c51e28e6"
        val currencies = "VND"
        val source = "USD"
        val format = 1

        val expectedCurrencyData = getCurrencyDataDto()
        Mockito.`when`(apiService.getCurrencyVND(accessKey, currencies, source, format))
            .thenReturn(expectedCurrencyData)

        val actualCurrencyData = currencyRepositoryImpl.getCurrencyFromServer()
        assertEquals(expectedCurrencyData.toCurrency(), actualCurrencyData)

    }

    private fun getCurrencyDataDto(): CurrencyDataDto {
        return CurrencyDataDto(privacy = "privacy", quotesDto = QuotesDto(24656f),
            source = "source", success = true, timestamp = 17626262, terms = "terms")
    }
}