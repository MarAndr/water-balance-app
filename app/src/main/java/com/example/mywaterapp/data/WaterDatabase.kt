package com.example.mywaterapp.data

import android.content.Context
import androidx.room.Room

object WaterDatabase {
    lateinit var instance: AppWaterDatabase

    fun init(context: Context){
        instance = Room.databaseBuilder(
            context,
            AppWaterDatabase::class.java,
            AppWaterDatabase.DB_NAME
        ).build()
    }
}

