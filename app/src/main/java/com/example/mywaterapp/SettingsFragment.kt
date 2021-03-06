package com.example.mywaterapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.alarmManager.service.AlarmService
import com.example.mywaterapp.databinding.FragmentSettingsBinding
import com.example.mywaterapp.dialogs.ChangeGengerDialog
import com.example.mywaterapp.dialogs.ChangeWeightDialog
import com.example.mywaterapp.utils.SavingSPHelper
import java.util.*

class SettingsFragment: ViewBindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {

    private val viewModel by viewModels<WaterBalanceViewModel>()
    private var currentGender: String? = null
    private var currentWeight: Int? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tvSettingsFragmentScheduleReminder.setOnClickListener {
        findNavController().navigate(R.id.action_settingsFragment2_to_scheduleReminderFragment2)
        }
        currentGender = SavingSPHelper.spUserSettings.getString(SavingSPHelper.USER_GENDER, "Male")
        currentWeight = SavingSPHelper.spUserSettings.getInt(SavingSPHelper.USER_WEIGHT, 0)
        setCurrentWeightInTextView(currentWeight = currentWeight)
        setCurrentGenderInTextView(currentGender = currentGender)

        binding.tvSettingsFragmentWeightCurrentValue.setOnClickListener {
            ChangeWeightDialog{ weight ->
                viewModel.saveWaterBalanceNormInSharedPreferences(currentGender!!,weight)
                setCurrentWeightInTextView(currentWeight = weight)
            }.show(childFragmentManager, "changeWeightDialog")
        }

        binding.tvSettingsFragmentGenderCurrentValue.setOnClickListener {
            ChangeGengerDialog{ gender ->
                viewModel.saveWaterBalanceNormInSharedPreferences(gender,currentWeight!!)
                setCurrentGenderInTextView(currentGender = gender)
            }.show(childFragmentManager, "changeGengerDialog")
        }
    }

    private fun setCurrentWeightInTextView(currentWeight: Int?){
        binding.tvSettingsFragmentWeightCurrentValue.text = currentWeight.toString()
    }

    private fun setCurrentGenderInTextView(currentGender: String?){
        binding.tvSettingsFragmentGenderCurrentValue.text = currentGender.toString()
    }
    }


