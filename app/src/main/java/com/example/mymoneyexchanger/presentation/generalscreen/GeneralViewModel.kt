package com.example.mymoneyexchanger.presentation.generalscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymoneyexchanger.data.MoneyExchangeRepositoryImpl
import com.example.mymoneyexchanger.domain.usecase.GetMoneyCursesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GeneralViewModel @Inject constructor(
    private val manyCurses: GetMoneyCursesUseCase,
    private val repository: MoneyExchangeRepositoryImpl
) : ViewModel() {
    companion object {
        private const val DEFAULT_VALUE = ""
    }

    private val _moneyCurs = MutableStateFlow(DEFAULT_VALUE)
    val moneyCurs: StateFlow<String>
        get() = _moneyCurs.asStateFlow()

    private val _exception = MutableStateFlow(DEFAULT_VALUE)
    val exception: StateFlow<String>
        get() = _exception.asStateFlow()

    fun getMoneyCurses(firstCurrency: String, secondCurrency: String, amount: String) {
        viewModelScope.launch(Dispatchers.IO) {

            try {
                val result = manyCurses.invoke(firstCurrency, secondCurrency)
                result.collect {
                    if (it.isNotEmpty()) {
                        _moneyCurs.value = "${countMoney(it, amount)} $secondCurrency"
                    }
                }
            } catch (e: Exception){
                _exception.value = e.toString()
                Log.i("MyLog", e.toString())
            }
        }
    }

    private suspend fun countMoney(amount: String, serverResponse: String): String {
        return viewModelScope.async(Dispatchers.IO) {
            repository.countMoneyRepo(amount, serverResponse)
        }.await()
    }
}