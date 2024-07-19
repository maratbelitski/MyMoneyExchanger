package com.example.mymoneyexchanger.data

import com.example.mymoneyexchanger.data.mapper.MoneyExchangeMapper
import com.example.mymoneyexchanger.data.remotesource.MoneyExchangeService
import com.example.mymoneyexchanger.domain.repository.MoneyExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoneyExchangeRepositoryImpl @Inject constructor(
    private val service: MoneyExchangeService,
    private val mapper: MoneyExchangeMapper
) : MoneyExchangeRepository {

    companion object{
        private const val COUNT_SIZE = "%.2f"
    }

    override suspend fun getMoneyCursesRepo(firstPair: String, secondPair: String): Flow<String> {
        val result = service.getMoneyCurses(firstPair, secondPair)
        return if (result.isSuccessful) {
            val responseServer = result.body()
            val mappedValue = responseServer?.let { mapper.moneyExchangeDtoToMoneyExchange(it) }
            flow { emit( mappedValue?.conversionRate.toString()) }
        } else {
            flow { emit("Error: ${result.code()}") }
        }
    }

    //API is not possible to change the amount, so I do it
   suspend fun countMoneyRepo(count: String, serverResponse: String): String {
        val response = serverResponse.toDouble()
        val value = count.toDouble()
        val result = response * value
        val formatted = String.format(COUNT_SIZE, result)
        return formatted
    }
}