package com.example.androidkotlinfundamentals.guesstheword.score

import android.util.Log
import androidx.lifecycle.ViewModel

class GuessScoreViewModel(private val finalScore: Int): ViewModel() {
    init {
        Log.i("Final Score","Final Score $finalScore")
    }
}
