package com.example.androidkotlinfundamentals.screens.trivia.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentTriviaGameOverBinding

class TriviaGameOverFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTriviaGameOverBinding>(inflater,R.layout.fragment_trivia_game_over,container,false)
        binding.tryAgainButton.setOnClickListener {
            it.findNavController().navigate(TriviaGameOverFragmentDirections.actionGameOverFragmentToGameFragment())
        }
        return binding.root
    }
}