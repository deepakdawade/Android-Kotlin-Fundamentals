package com.example.androidkotlinfundamentals.marsrealestate.marsoverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidkotlinfundamentals.marsrealestate.network.MarsApi
import com.example.androidkotlinfundamentals.marsrealestate.network.MarsApiFilter
import com.example.androidkotlinfundamentals.marsrealestate.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, SUCCESS }
class MarsOverviewViewModel : ViewModel() {
    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status
    private val _properties: MutableLiveData<List<MarsProperty>> = MutableLiveData<List<MarsProperty>>()
    val properties : LiveData<List<MarsProperty>>
        get() = _properties
    private val _navigateToselectedProperty = MutableLiveData<MarsProperty>()
    val navigateToselectedProperty : LiveData<MarsProperty>
    get() = _navigateToselectedProperty
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope( Dispatchers.Main + viewModelJob)

    init {
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }
    private fun getMarsRealEstateProperties(filter: MarsApiFilter){
        coroutineScope.launch {
            val getPropetiesDeferred = MarsApi.retrofitService.getProperties(filter.value)
            try {
                _status.value = MarsApiStatus.LOADING
                val listResult = getPropetiesDeferred.await()
                if (listResult.size > 0)
                    _properties.value = listResult
                _status.value = MarsApiStatus.SUCCESS
            }catch (t:Throwable){
                _status.value = MarsApiStatus.ERROR
            }
        }
    }

    fun displayPropertyDetails(marsProperty: MarsProperty){
        _navigateToselectedProperty.value = marsProperty
    }
    fun displayPropertyDetailsComplete(){
        _navigateToselectedProperty.value = null
    }

    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
//    private val _status = MutableLiveData<MarsApiStatus>()
//    val status: LiveData<MarsApiStatus>
//        get() = _status
//    private val _properties = MutableLiveData<List<MarsProperty>>()
//
//    val properties: LiveData<List<MarsProperty>>
//        get() = _properties
//
//    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
//    val navigateToSelectedProperty: LiveData<MarsProperty>
//        get() = _navigateToSelectedProperty
//
//    private var viewModelJob = Job()
//
//    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//    init {
//        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
//    }
//
//    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
//        coroutineScope.launch {
//            val getPropertiesDeferred = MarsApi.retrofitService.getProperties(filter.value)
//            try {
//                _status.value = MarsApiStatus.LOADING
//                val listResult = getPropertiesDeferred.await()
//                _status.value = MarsApiStatus.DONE
//                _properties.value = listResult
//            } catch (e: Exception) {
//                _status.value = MarsApiStatus.ERROR
//                _properties.value = ArrayList()
//            }
//        }
//    }
//    override fun onCleared() {
//        super.onCleared()
//        viewModelJob.cancel()
//    }
//
//    fun displayPropertyDetails(marsProperty: MarsProperty) {
//        _navigateToSelectedProperty.value = marsProperty
//    }
//
//    fun displayPropertyDetailsComplete() {
//        _navigateToSelectedProperty.value = null
//    }
//    fun updateFilter(filter: MarsApiFilter) {
//        getMarsRealEstateProperties(filter)
//    }
//}