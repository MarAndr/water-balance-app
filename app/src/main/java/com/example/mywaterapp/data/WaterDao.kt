package com.example.mywaterapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WaterDao {
    @Insert
    suspend fun addWater(water: List<DrinkingWater>)

    @Query("select * from ${WaterContract.TABLE_NAME}")
    suspend fun getAllWater(): List<DrinkingWater>

    @Query("select * from ${WaterContract.TABLE_NAME}")
    fun getAllWaterLiveData(): LiveData<List<DrinkingWater>>


}