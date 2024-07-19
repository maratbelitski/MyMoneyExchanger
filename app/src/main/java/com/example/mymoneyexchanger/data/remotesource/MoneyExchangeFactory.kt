package com.example.mymoneyexchanger.data.remotesource

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MoneyExchangeFactory {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v6.exchangerate-api.com/v6/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val moneyExchangeService = retrofit.create(MoneyExchangeService::class.java)
}