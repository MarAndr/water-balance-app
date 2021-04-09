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
import com.example.mywaterapp.alarmManager.service.AlarmService
import com.example.mywaterapp.ViewBindingFragment
import com.example.mywaterapp.databinding.FragmentMainAlarmBinding
import java.util.*

class MainAlarmFragment: ViewBindingFragment<FragmentMainAlarmBinding>(FragmentMainAlarmBinding::inflate) {

    lateinit var alarmService: AlarmService

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        alarmService = AlarmService(requireContext())
        binding.btnMainFragmentAlarmSetExact.setOnClickListener {
            setAlarm(){
                alarmService.setExactAlarm(it)
            }
        }

        binding.btnMainFragmentAlarmSetRepetitive.setOnClickListener {
//            setAlarm(){
//                alarmService.setHourAlarm(it)
//            }

            buildNotification(requireContext(), "test", "testMessage")
        }
    }

    private fun buildNotification(context: Context, title: String, message: String){
        val notification = NotificationCompat.Builder(context, NotificationChannels.MESSAGE_CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_baseline_settings)
            .build()

        NotificationManagerCompat.from(context).notify(1, notification)
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
                        { _, hourOfDay, minute ->
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
}