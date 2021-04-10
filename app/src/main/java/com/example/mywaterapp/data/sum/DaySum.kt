package com.example.mywaterapp.data.sum

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DaySumsContract.TABLE_NAME)
data class DaySum(
        @PrimaryKey
        @ColumnInfo(name = DaySumsContract.Columns.DAY)
        val day: String,
        @ColumnInfo(name = DaySumsContract.Columns.SUM)
        val sum: Double
)