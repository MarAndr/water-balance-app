package com.example.mywaterapp.data.sum

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaySumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDaySum(daySum: List<DaySum>)

    @Query("select * from ${DaySumsContract.TABLE_NAME} where ${DaySumsContract.Columns.DAY} = :day")
    suspend fun getDaySum(day: String): List<DaySum>

    @Query("select * from ${DaySumsContract.TABLE_NAME}")
    suspend fun getAllDaysSum(): List<DaySum>
}