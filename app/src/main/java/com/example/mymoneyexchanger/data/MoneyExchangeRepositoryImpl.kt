package com.example.mymoneyexchanger.data

import com.example.mymoneyexchanger.data.remotesource.MoneyExchangeService
import com.example.mymoneyexchanger.domain.repository.MoneyExchangeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoneyExchangeRepositoryImpl @Inject constructor(
    private val service: MoneyExchangeService
) : MoneyExchangeRepository {

    companion object{
        private const val COUNT_SIZE = "%.3f"
    }

    override suspend fun getMoneyCursesRepo(firstPair: String, secondPair: String): Flow<String> {
        val result = service.getMoneyCurses(firstPair, secondPair)
        return if (result.isSuccessful) {
            flow { emit(result.body()?.conversionRate .toString()) }
        } else {
            flow { emit("Error: ${result.code()}") }
        }
    }

    //API is not possible to change the amount, so i do it
   suspend fun countMoneyRepo(count: String, serverResponse: String): String {
        val response = serverResponse.toDouble()
        val value = count.toDouble()
        val result = response * value
        val formatted = String.format(COUNT_SIZE, result)
        return formatted
    }
}