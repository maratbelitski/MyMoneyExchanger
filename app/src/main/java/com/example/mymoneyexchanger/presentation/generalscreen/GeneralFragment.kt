package com.example.mymoneyexchanger.presentation.generalscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.mymoneyexchanger.R
import com.example.mymoneyexchanger.databinding.FragmentGeneralBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GeneralFragment : Fragment() {

    private var _binding: FragmentGeneralBinding? = null
    private val binding: FragmentGeneralBinding
        get() = _binding ?: throw NullPointerException()

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
                val firstPair = spinnerFirstPair.selectedItem.toString()
                val secondPair = spinnerSecondPair.selectedItem.toString()

                viewModel.getMoneyCurses(firstPair, secondPair)
            }
        }
    }

    private fun onObservers() {
        lifecycleScope.launch {
            viewModel.moneyCurs
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it.isNotEmpty()) {
                        var sumInput = binding.etSumInput.text.toString()
                        if (sumInput.isEmpty()) sumInput = "1"

                        binding.tvResult.text = viewModel.countMoney(sumInput, it)
                    }
                }
        }
    }

    private fun initViews() {
        val listCurrency = listOf("BYN", "USD", "RUB")
        val myAdapter = ArrayAdapter(requireActivity(), R.layout.spinner_text_item, listCurrency)

        binding.spinnerFirstPair.adapter = myAdapter
        binding.spinnerSecondPair.adapter = myAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}