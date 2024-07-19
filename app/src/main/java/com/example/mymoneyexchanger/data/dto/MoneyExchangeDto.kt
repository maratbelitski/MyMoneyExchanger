package com.example.mymoneyexchanger.data.dto

import com.google.gson.annotations.SerializedName

data class MoneyExchangeDto(
    @SerializedName("base_code")
    val firstPair: String,
    @SerializedName("conversion_rate")
    val conversionRate: Double,
    @SerializedName("target_code")
    val secondPair: String,
)