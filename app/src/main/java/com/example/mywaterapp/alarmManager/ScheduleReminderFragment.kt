package com.example.mywaterapp.alarmManager

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mywaterapp.R
import com.example.mywaterapp.ViewBindingFragment
import com.example.mywaterapp.alarmManager.service.AlarmService
import com.example.mywaterapp.databinding.FragmentScheduleReminderBinding
import kotlinx.android.synthetic.main.fragment_schedule_reminder.*
import timber.log.Timber
import java.util.*

class ScheduleReminderFragment: ViewBindingFragment<FragmentScheduleReminderBinding>(FragmentScheduleReminderBinding::inflate) {

    lateinit var alarmService: AlarmService

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        alarmService = AlarmService(requireContext())
        binding.btnScheduleReminderFragmentConfirm.setOnClickListener {
//            when {
//                binding.switchScheduleReminderFragmentOnHour.isChecked -> {
//                    setAlarm{
//                        alarmService.setHourAlarm(it)
//                    }
//                }
//                binding.switchScheduleReminderFragmentOn2Hour.isChecked -> {
//                    setAlarm{
//                        alarmService.set2HourAlarm(it)
//                    }
//                }
//                binding.switchScheduleReminderFragmentOn3Hour.isChecked -> {
//                    setAlarm{
//                        alarmService.set3HourAlarm(it)
//                    }
//                }
//            }
//        }
//
//        binding.btnScheduleReminderFragmentTest.setOnClickListener {
//            setAlarm{
//                alarmService.setExactAlarm(it)
//            }
//        }
    }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun setAlarm(callback: (Long) -> Unit){
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            DatePickerDialog(
                requireContext(),
                0,
                { _, year, month, dayOfMonth ->
                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    TimePickerDialog(
                        requireContext(),
                        0,
                        TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                            this.set(Calendar.HOUR_OF_DAY, hourOfDay)
                            this.set(Calendar.MINUTE, minute)
                            callback(this.timeInMillis)
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()
                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH),
            ).show()
        }
    }

//    fun isSwitchHourChecked() = binding.switchScheduleReminderFragmentOnHour.isChecked
}