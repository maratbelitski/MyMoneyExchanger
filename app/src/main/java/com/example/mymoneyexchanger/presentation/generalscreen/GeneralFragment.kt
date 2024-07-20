package com.example.mymoneyexchanger.presentation.generalscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.mymoneyexchanger.R
import com.example.mymoneyexchanger.databinding.FragmentGeneralBinding
import com.example.mymoneyexchanger.utils.MoneyConstants.BYN
import com.example.mymoneyexchanger.utils.MoneyConstants.RUB
import com.example.mymoneyexchanger.utils.MoneyConstants.EUR
import com.example.mymoneyexchanger.utils.MoneyConstants.USD
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneralFragment : Fragment() {

    private var _binding: FragmentGeneralBinding? = null
    private val binding: FragmentGeneralBinding
        get() = _binding ?: throw NullPointerException()

    companion object {
        const val DEFAULT_AMOUNT = "1"
    }

    private val viewModel: GeneralViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGeneralBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        onObservers()
        onListeners()
    }

    private fun onListeners() {
        binding.btnAlculate.setOnClickListener {
            with(binding) {
                var amount = binding.etSumInput.text.toString()
                if (amount.isEmpty()) amount = DEFAULT_AMOUNT
                val firstPair = spinnerFirstPair.selectedItem.toString()
                val secondPair = spinnerSecondPair.selectedItem.toString()

                viewModel.getMoneyCurses(firstPair, secondPair, amount)
            }
        }
    }

    private fun onObservers() {
        lifecycleScope.launch {
            viewModel.moneyCurs
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    binding.tvResult.text = it
                }
        }

        lifecycleScope.launch {
            viewModel.exception
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it.isNotEmpty()) showToast(it)
                }
        }
    }

    private fun initViews() {
        val listCurrency = listOf(BYN, USD, RUB, EUR)
        val myAdapter = ArrayAdapter(requireActivity(), R.layout.spinner_text_item, listCurrency)

        binding.spinnerFirstPair.adapter = myAdapter
        binding.spinnerSecondPair.adapter = myAdapter
    }

    private fun showToast(text:String){
        Toast.makeText(requireActivity(), text, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}