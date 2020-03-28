package com.example.androidkotlinfundamentals.trackmysleepquality.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality_table")
data class SleepNight(
    @PrimaryKey(autoGenerate = true)
    var nightId:Long = 0,
    @ColumnInfo(name = "start_time_millis")
    val startTimeMillis:Long = System.currentTimeMillis(),
    @ColumnInfo(name = "end_time_millis")
    var endTimeMillis: Long = startTimeMillis,

    @ColumnInfo(name = "sleep_quality")
    var sleepQuality:Int = -1
)

