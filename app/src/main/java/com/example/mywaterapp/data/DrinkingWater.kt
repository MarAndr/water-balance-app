package com.example.mywaterapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = WaterContract.TABLE_NAME)
data class DrinkingWater(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = WaterContract.Columns.ID)
        val id: Long,
        @ColumnInfo(name = WaterContract.Columns.TIME)
        val time: String,
        @ColumnInfo(name = WaterContract.Columns.DAY)
        val day: String,
        @ColumnInfo(name = WaterContract.Columns.VOLUME)
        val volume: Long
)
