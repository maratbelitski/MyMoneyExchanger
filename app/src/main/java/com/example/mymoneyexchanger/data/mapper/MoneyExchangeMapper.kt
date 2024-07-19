package com.example.mymoneyexchanger.data.mapper
import com.example.mymoneyexchanger.data.dto.MoneyExchangeDto
import com.example.mymoneyexchanger.domain.model.MoneyExchange
import javax.inject.Inject

class MoneyExchangeMapper @Inject constructor() {
    fun moneyExchangeDtoToMoneyExchange(moneyExchangeDto: MoneyExchangeDto): MoneyExchange {

        return MoneyExchange(
            firstPair = moneyExchangeDto.firstPair,
            secondPair = moneyExchangeDto.secondPair,
            conversionRate = moneyExchangeDto.conversionRate
        )
    }
}