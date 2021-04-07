package com.example.mywaterapp

import androidx.lifecycle.LiveData
import com.example.mywaterapp.data.DrinkingWater
import com.example.mywaterapp.data.WaterDatabase
import com.example.mywaterapp.ui.home.SavingSPHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WaterBalanceRepository {

    private val waterDao = WaterDatabase.instance.waterDao()

    suspend fun getDrinkingWater(): List<DrinkingWater>{
        return waterDao.getAllWater()
    }

    fun getDrinkingWaterLiveData(): LiveData<List<DrinkingWater>>{
        return waterDao.getAllWaterLiveData()
    }

    suspend fun addWater(waterList: List<DrinkingWater>){
        waterDao.addWater(waterList)
    }

    suspend fun saveWaterBalanceNormInSharedPreferences(sex: String, weight: Int) = withContext(Dispatchers.IO){
        val waterNorm = if (sex == "Male") weight * 35 else weight * 31
        SavingSPHelper.spWaterNorm.edit()
            .putString("waterNorm", waterNorm.toString())
            .apply()
    }


}