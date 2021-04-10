package com.example.mywaterapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mywaterapp.data.AppWaterDatabase.Companion.DB_VERSION
import com.example.mywaterapp.data.sum.DaySum
import com.example.mywaterapp.data.sum.DaySumDao

@Database(entities = [DrinkingWater::class, DaySum::class], version = DB_VERSION)
abstract class AppWaterDatabase: RoomDatabase() {
    companion object{
        const val DB_VERSION = 1
        const val DB_NAME = "water_db"
    }

    abstract fun waterDao(): WaterDao
    abstract fun daySumDao(): DaySumDao
}