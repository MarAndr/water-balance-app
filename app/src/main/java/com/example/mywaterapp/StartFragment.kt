package com.example.mywaterapp

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.databinding.FragmentStartBinding
import com.example.mywaterapp.utils.SavingSPHelper

class StartFragment: ViewBindingFragment<FragmentStartBinding>(FragmentStartBinding::inflate) {

    private val viewModel: WaterBalanceViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        SavingSPHelper.initHelper(requireContext())
        viewModel.checkIsItFirstRun()
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.isItFirstRun.observe(viewLifecycleOwner) { isItFirstRun ->
            if (isItFirstRun){
                findNavController().navigate(R.id.action_startFragment_to_fragmentStartOptions)
            } else{
                findNavController().navigate(R.id.action_startFragment_to_mainFragment)
            }

        }
    }
}