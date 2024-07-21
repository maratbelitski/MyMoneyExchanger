package com.example.mymoneyexchanger.domain.repository

import kotlinx.coroutines.flow.Flow


interface MoneyExchangeRepository {
   suspend fun getMoneyCursesRepo(firstCurrency: String, secondCurrency: String): Flow<String>
}