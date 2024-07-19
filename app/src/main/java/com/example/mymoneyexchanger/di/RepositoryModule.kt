package com.example.mymoneyexchanger.di

import com.example.mymoneyexchanger.data.MoneyExchangeRepositoryImpl
import com.example.mymoneyexchanger.domain.repository.MoneyExchangeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMoneyExchangeRepository(repositoryImpl: MoneyExchangeRepositoryImpl): MoneyExchangeRepository
}