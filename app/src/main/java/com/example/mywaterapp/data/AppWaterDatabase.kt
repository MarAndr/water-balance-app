package com.example.mywaterapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mywaterapp.data.AppWaterDatabase.Companion.DB_VERSION

@Database(entities = [DrinkingWater::class], version = DB_VERSION)
abstract class AppWaterDatabase: RoomDatabase() {
    companion object{
        const val DB_VERSION = 1
        const val DB_NAME = "water_db"
    }

    abstract fun waterDao(): WaterDao
}