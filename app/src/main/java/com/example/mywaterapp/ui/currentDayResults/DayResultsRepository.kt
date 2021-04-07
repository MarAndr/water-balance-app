package com.example.mywaterapp.ui.currentDayResults

import android.util.Log
import com.example.mywaterapp.ui.home.SavingSPHelper

class DayResultsRepository {

    companion object{
        private const val TAG = "DayResultsRepository"
    }

    fun getVolumesSum(): Int{
        val volumesList= SavingSPHelper.spWaterNorm.all.mapNotNull { spData ->
            Log.d(TAG, "getVolumesSum: listVolumes = $spData")
            spData.value.toString().toInt()
        }
        Log.d(TAG, "getVolumesSum: sum = ${volumesList.sum()}")
        return volumesList.sum()
    }
}