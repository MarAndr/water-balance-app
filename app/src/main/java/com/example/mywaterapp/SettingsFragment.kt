package com.example.mywaterapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.mywaterapp.alarmManager.service.AlarmService
import com.example.mywaterapp.databinding.FragmentSettingsBinding
import java.util.*

class SettingsFragment: ViewBindingFragment<FragmentSettingsBinding>(FragmentSettingsBinding::inflate) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.tvSettingsFragmentScheduleReminder.setOnClickListener {
        findNavController().navigate(R.id.action_settingsFragment_to_scheduleReminderFragment)
        }
    }


}