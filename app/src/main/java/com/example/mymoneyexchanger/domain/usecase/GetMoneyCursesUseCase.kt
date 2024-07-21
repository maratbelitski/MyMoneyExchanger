package com.example.mymoneyexchanger.domain.usecase

import com.example.mymoneyexchanger.domain.repository.MoneyExchangeRepository
import javax.inject.Inject

class GetMoneyCursesUseCase @Inject constructor(
    private val repository: MoneyExchangeRepository
) {
    suspend operator fun invoke(firstCurrency: String, secondCurrency: String) =
        repository.getMoneyCursesRepo(firstCurrency, secondCurrency)
}