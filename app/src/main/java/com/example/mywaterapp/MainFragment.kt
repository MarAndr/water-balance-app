package com.example.mywaterapp

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.mywaterapp.databinding.FragmentMainBinding
import com.example.mywaterapp.ui.home.SavingSPHelper
import com.example.mywaterapp.ui.home.SetVolumeDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class MainFragment: ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: WaterBalanceViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        SavingSPHelper.initHelper(requireContext())
        val waterNorm = SavingSPHelper.spWaterNorm.getString("waterNorm", "0")
        tv_homeFragment_waterGoal.text = waterNorm
        viewModel.checkIsItFirstRun()
        viewModel.getAllWater()
        viewModel.getDaySum()
        binding.btnHomeFragmentDrink.setOnClickListener {
            createSetVolumeDialog()
        }
        observeLiveData()
    }


    private fun createSetVolumeDialog(){
        SetVolumeDialog(viewModel).show(childFragmentManager.beginTransaction(), "setVolumeDialog")
    }

    private fun observeLiveData(){
        viewModel.daySum.observe(viewLifecycleOwner){
            try {
                binding.tvHomeFragmentCurrentValue.text = it.toString()
            } catch (e: NullPointerException) {
                Timber.d(e)
            }
        }

        viewModel.isItFirstRun.observe(viewLifecycleOwner) { isItFirstRun ->
            if (isItFirstRun)
            StartOptionDialogFragment().show(childFragmentManager, "mainOptionDialog")
        }
    }

    private fun makeSnackbar(message: String){
        Snackbar.make(binding.containerHomeFragment, message, Snackbar.LENGTH_SHORT).show()
    }
}