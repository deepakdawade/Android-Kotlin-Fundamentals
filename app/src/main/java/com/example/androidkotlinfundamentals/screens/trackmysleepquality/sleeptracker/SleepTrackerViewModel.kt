package com.example.androidkotlinfundamentals.screens.trackmysleepquality.sleeptracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.androidkotlinfundamentals.formatNights
import com.example.androidkotlinfundamentals.screens.trackmysleepquality.database.SleepDatabaseDao
import com.example.androidkotlinfundamentals.screens.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*

class SleepTrackerViewModel(private val sleepDatabaseDao: SleepDatabaseDao,
                            application: Application): AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val tonight = MutableLiveData<SleepNight>()
    private val _nights = sleepDatabaseDao.getAllNight()
    val nights :LiveData<List<SleepNight>> = _nights
    val nightsString = Transformations.map(_nights) { nights ->
        formatNights(nights, application.resources)
    }
    val startButtonVisible = Transformations.map(tonight) {
        null == it
    }
    val stopButtonVisible = Transformations.map(tonight) {
        null != it
    }
    val clearButtonVisible = Transformations.map(_nights) {
        it?.isNotEmpty()
    }

    private var _showSnackbarEvent = MutableLiveData<Boolean>()

    val showSnackBarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent
    private val _navigateToSleepQuality = MutableLiveData<SleepNight>()

    val navigateToSleepQuality: LiveData<SleepNight>
        get() = _navigateToSleepQuality
    private val _navigateToSleepDataQuality = MutableLiveData<Long>()
    val navigateToSleepDataQuality
        get() = _navigateToSleepDataQuality
    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {

            tonight.value = getTonightFromDataBase()
        }
    }

    private suspend fun getTonightFromDataBase(): SleepNight? {
        return withContext(Dispatchers.IO){
            var night = sleepDatabaseDao.getTonight()
            if (night?.endTimeMillis != night?.startTimeMillis)
                night = null
            night
        }
    }
    fun onStartTracking(){
        uiScope.launch {
            val newNight = SleepNight()
            insert(newNight)
            tonight.value = getTonightFromDataBase()
        }
    }

    private suspend fun insert(night: SleepNight) {
        withContext(Dispatchers.IO){
            sleepDatabaseDao.insert(night)
        }
    }
    fun onStopTracking(){
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMillis = System.currentTimeMillis()
            update(oldNight)
            _navigateToSleepQuality.value = oldNight
        }
    }

    private suspend fun update(night: SleepNight) {
        withContext(Dispatchers.IO){
            sleepDatabaseDao.update(night)
        }
    }
    fun onClear(){
        uiScope.launch {
            clear()
            tonight.value = null
        }
    }
    private suspend fun clear(){
        withContext(Dispatchers.IO){
            sleepDatabaseDao.clear()

        }
    }
    fun doneNavigating() {
        _navigateToSleepQuality.value = null
    }
    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
        _showSnackbarEvent.value = true
    }

    fun onSleepNightClicked(sleepId: Long) {
        _navigateToSleepDataQuality.value = sleepId
    }
    fun onSleepDataQualityNavigated() {
        _navigateToSleepDataQuality.value = null
    }
}