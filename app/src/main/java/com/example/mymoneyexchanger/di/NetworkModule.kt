package com.example.mymoneyexchanger.di

import com.example.mymoneyexchanger.data.remotesource.MoneyExchangeFactory
import com.example.mymoneyexchanger.data.remotesource.MoneyExchangeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): MoneyExchangeFactory {
        return MoneyExchangeFactory()
    }

    @Provides
    @Singleton
    fun provideRetrofitService(retrofitFactory:MoneyExchangeFactory): MoneyExchangeService {
        return retrofitFactory.moneyExchangeService
    }
}