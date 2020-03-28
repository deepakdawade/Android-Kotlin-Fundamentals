package com.example.androidkotlinfundamentals.guesstheword.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GuessScoreViewModelFactory(private val finalScore:Int): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuessScoreViewModel::class.java))
                return GuessScoreViewModel(finalScore) as T
            throw IllegalArgumentException("Unknown ViewModel")
    }
}