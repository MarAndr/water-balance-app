package com.example.mywaterapp

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.databinding.FragmentOptionBinding

class FragmentStartOptions: ViewBindingFragment<FragmentOptionBinding>(FragmentOptionBinding::inflate) {

    private val viewModel: WaterBalanceViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnStartOptionFragment.setOnClickListener {
            val weight = binding.etStartOptionFragmentWeight.text.toString()
            viewModel.saveWaterBalanceNormInSharedPreferences(getRadioButtonValue(), weight.toInt())
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