/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentGuessScoreBinding
import com.example.androidkotlinfundamentals.guesstheword.score.GuessScoreViewModel
import com.example.androidkotlinfundamentals.guesstheword.score.GuessScoreViewModelFactory

/**
 * Fragment where the final score is shown, after the game is over
 */
class GuessScoreFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentGuessScoreBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_guess_score,
                container,
                false
        )

        val guessScoreViewModelFactory = GuessScoreViewModelFactory(GuessScoreFragmentArgs.fromBundle(arguments!!).score )
        val guessScoreViewModel = ViewModelProviders.of(this,guessScoreViewModelFactory).get(GuessScoreViewModel::class.java)
        val scoreFragmentArgs by navArgs<GuessScoreFragmentArgs>()
        binding.scoreViewModel = guessScoreViewModel
        binding.scoreText.text = scoreFragmentArgs.score.toString()
        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(GuessScoreFragmentDirections.actionGuessScoreFragmentToGuessGameFragment())
        }
        return binding.root
    }
}
