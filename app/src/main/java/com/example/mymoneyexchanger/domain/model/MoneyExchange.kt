package com.example.mymoneyexchanger.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoneyExchange(
    val firstCurrency: String,
    val conversionRate: Double,
    val secondCurrency: String,
): Parcelable