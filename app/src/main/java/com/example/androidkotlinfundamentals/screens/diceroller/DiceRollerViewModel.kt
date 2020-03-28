package com.example.androidkotlinfundamentals.screens.diceroller

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DiceRollerViewModel : ViewModel() {
    private val _textCounter :MutableLiveData<Int> by lazy {
        MutableLiveData(0)
    }
    val textCounter : LiveData<Int>
        get() = _textCounter
    fun generateRandomNumber(){
        val random = (1..6).random()
        _textCounter.value = random
    }
    fun countUp(){
        _textCounter.value = (_textCounter.value?.plus(1))
    }


}