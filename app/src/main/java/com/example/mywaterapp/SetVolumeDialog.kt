package com.example.mywaterapp

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mywaterapp.data.DrinkingWater
import com.example.mywaterapp.utils.getCurrentTime
import kotlinx.android.synthetic.main.dialog_setvolume.view.*
import timber.log.Timber

class SetVolumeDialog(private val viewModel: WaterBalanceViewModel): DialogFragment() {

    companion object{
        private const val TAG = "SetVolumeDialog"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_setvolume, null)
        return AlertDialog.Builder(requireContext())
                .setView(view)
                .setPositiveButton("ok"){_,_ ->
                    val volume = view.et_dialog_setVolume.text.toString().toLongOrNull()?:0
                    val time = getCurrentTime()
                    Timber.d("time: $time")
                    viewModel.addWater(listOf(DrinkingWater(id = 0, time = time, volume = volume)))
                }
                .create()
    }
}