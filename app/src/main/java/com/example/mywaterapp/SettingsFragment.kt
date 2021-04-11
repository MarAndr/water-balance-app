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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tvSettingsFragmentScheduleReminder.setOnClickListener {
        findNavController().navigate(R.id.action_settingsFragment2_to_scheduleReminderFragment2)
        }
        binding.tvSettingsFragmentGenderCurrentValue.text = SavingSPHelper.spUserSettings.getString(SavingSPHelper.USER_GENDER, "Male")
        binding.tvSettingsFragmentWeightCurrentValue.text =
            SavingSPHelper.spUserSettings.getInt(SavingSPHelper.USER_WEIGHT, 0).toString()

        binding.tvSettingsFragmentWeightCurrentValue.setOnClickListener {
            ChangeWeightDialog(viewModel).show(childFragmentManager, "changeWeightDialog")
        }

        binding.tvSettingsFragmentGenderCurrentValue.setOnClickListener {
            ChangeGengerDialog(viewModel).show(childFragmentManager, "changeGengerDialog")
        }
    }


}