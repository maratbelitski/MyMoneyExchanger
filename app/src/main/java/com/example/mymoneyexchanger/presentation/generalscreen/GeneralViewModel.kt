package com.example.mymoneyexchanger.presentation.generalscreen

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

    fun getMoneyCurses(firstPair: String, secondPair: String, amount: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val result = manyCurses.invoke(firstPair, secondPair)
            result.collect {
                if (it.isNotEmpty()) {
                    _moneyCurs.value = "${countMoney(it, amount)} $secondPair"
                }
            }
        }
    }

    private suspend fun countMoney(amount: String, serverResponse: String): String {
        return viewModelScope.async(Dispatchers.IO) {
            repository.countMoneyRepo(amount, serverResponse)
        }.await()
    }
}