package com.example.mywaterapp

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.data.DrinkingWater
import com.example.mywaterapp.databinding.FragmentMainBinding
import com.example.mywaterapp.utils.SavingSPHelper
import com.example.mywaterapp.utils.getCurrentDay
import com.example.mywaterapp.utils.getCurrentTime
import com.example.mywaterapp.utils.getDayFromFullTime
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber

class MainFragment: ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: WaterBalanceViewModel by viewModels()
    private var waterNorm = ""

    override fun onResume() {
        super.onResume()
        waterNorm = SavingSPHelper.spWaterNorm.getString("waterNorm", "0").toString()
        tv_homeFragment_waterGoal.text = getString(R.string.water_norm, waterNorm)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated")
        SavingSPHelper.initHelper(requireContext())
        viewModel.addWater(listOf(DrinkingWater(id = 0, time = getCurrentTime(), volume = 0, day = getCurrentDay())))
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

        viewModel.waterBalanceLiveData.observe(viewLifecycleOwner){ waterList ->
            val currentDay = getCurrentDay()
            val todayList = waterList.mapNotNull { it.takeIf { currentDay == getDayFromFullTime(it.time) } }
            val sum = todayList.sumBy {   drinkingWater ->
                drinkingWater.volume.toInt()
            }
            binding.tvHomeFragmentCurrentValue.text = sum.toString()
        }

    }


    private fun makeSnackbar(message: String){
        Snackbar.make(binding.containerHomeFragment, message, Snackbar.LENGTH_SHORT).show()
    }
}