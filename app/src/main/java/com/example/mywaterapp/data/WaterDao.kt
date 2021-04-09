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

//    @Query("select sum(${DoctorsContract.Columns.AGE}) from ${DoctorsContract.TABLE_NAME}")
//    fun allWaterSum() : LiveData<Double>

}