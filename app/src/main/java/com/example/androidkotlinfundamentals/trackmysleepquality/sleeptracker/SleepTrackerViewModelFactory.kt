package com.example.androidkotlinfundamentals.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidkotlinfundamentals.trackmysleepquality.database.SleepDatabaseDao

class SleepTrackerViewModelFactory(private val sleepDatabaseDao: SleepDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java))
            return SleepTrackerViewModel(sleepDatabaseDao,application) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}