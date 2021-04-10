package com.example.mywaterapp

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.databinding.FragmentOptionBinding
import com.example.mywaterapp.utils.Weight

class FragmentStartOptions: ViewBindingFragment<FragmentOptionBinding>(FragmentOptionBinding::inflate) {

    private val viewModel: WaterBalanceViewModel by viewModels()
    private lateinit var weightChoiced: String

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.scrollChoice.addItems(Weight.weights,70)
        binding.scrollChoice.visibleItemCount = 2
        binding.scrollChoice.setOnItemSelectedListener { scrollChoice, position, name ->
            weightChoiced = name
        }

        binding.btnStartOptionFragment.setOnClickListener {
            viewModel.saveWaterBalanceNormInSharedPreferences(getRadioButtonValue(), weightChoiced.toInt())
            findNavController().navigate(R.id.action_fragmentStartOptions_to_mainFragment)
        }
    }

    private fun getRadioButtonValue(): String{
        return when (binding.radioGroupStartOptionFragment.checkedRadioButtonId){
            R.id.radioButton_startOptionFragment_male -> "Male"
            R.id.radioButton_startOptionFragment_female -> "Female"
            else -> ""
        }
    }
}