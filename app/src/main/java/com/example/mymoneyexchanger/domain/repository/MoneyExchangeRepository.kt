package com.example.mymoneyexchanger.domain.repository

import kotlinx.coroutines.flow.Flow


interface MoneyExchangeRepository {
   suspend fun getMoneyCursesRepo(firstPair: String, secondPair: String): Flow<String>
}