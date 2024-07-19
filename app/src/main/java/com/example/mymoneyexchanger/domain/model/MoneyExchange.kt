package com.example.mymoneyexchanger.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoneyExchange(
    val firstPair: String,
    val conversionRate: Double,
    val secondPair: String,
): Parcelable