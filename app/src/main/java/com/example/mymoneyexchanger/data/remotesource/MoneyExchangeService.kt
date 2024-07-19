package com.example.mymoneyexchanger.data.remotesource

import com.example.mymoneyexchanger.data.dto.MoneyExchangeDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MoneyExchangeService {
    @Headers("Authorization: Bearer aef046e35685dc5ea51f92e7")
    @GET("pair/{base_code}/{target_code}")
    suspend fun getMoneyCurses(
        @Path("base_code") firstPair: String,
        @Path("target_code") secondPair: String
    ): Response<MoneyExchangeDto>
}