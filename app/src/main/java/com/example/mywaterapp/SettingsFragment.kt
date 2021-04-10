package com.example.mywaterapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.alarmManager.service.AlarmService
import com.example.mywaterapp.databinding.FragmentSettingsBinding
import com.example.mywaterapp.dialogs.ChangeGengerDialog
import com.example.mywaterapp.dialogs.ChangeWeightDialog
import com.example.mywaterapp.utils.SavingSPHelper
import java.util.*

class SettingsFragment: ViewBindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tvSettingsFragmentScheduleReminder.setOnClickListener {
        findNavController().navigate(R.id.action_settingsFragment_to_scheduleReminderFragment)
        }
        binding.tvSettingsFragmentGenderCurrentValue.text = SavingSPHelper.spUserSettings.getString(SavingSPHelper.USER_GENDER, "Male")
        binding.tvSettingsFragmentWeightCurrentValue.text =
            SavingSPHelper.spUserSettings.getInt(SavingSPHelper.USER_WEIGHT, 0).toString()

        binding.tvSettingsFragmentWeightCurrentValue.setOnClickListener {
            ChangeWeightDialog().show(childFragmentManager, "changeWeightDialog")
        }

        binding.tvSettingsFragmentGenderCurrentValue.setOnClickListener {
            ChangeGengerDialog().show(childFragmentManager, "changeGengerDialog")
        }
    }


}