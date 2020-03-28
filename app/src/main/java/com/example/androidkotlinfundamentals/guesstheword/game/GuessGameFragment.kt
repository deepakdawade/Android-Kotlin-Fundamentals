package com.example.androidkotlinfundamentals.guesstheword.game

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.screens.game.GuessGameViewModel
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentGuessGameBinding

/**
 * Fragment where the game is played
 */
class GuessGameFragment : Fragment() {

    private lateinit var viewModel: GuessGameViewModel

    private lateinit var binding: FragmentGuessGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_guess_game,
            container,
            false
        )

        // Get the viewmodel
        Log.i("GameFragment", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(GuessGameViewModel::class.java)
        binding.lifecycleOwner = this
        binding.gameViewModel = viewModel
        viewModel.eventGameFinished.observe(viewLifecycleOwner, Observer {  hasFinished->
            if (hasFinished) {
                gameFinished()
                viewModel.onGameFinished(false)
            }
        })
        viewModel.eventBuzz.observe(viewLifecycleOwner, Observer { buzzType ->
            if (buzzType != GuessGameViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })
        return binding.root

    }
    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

    private fun gameFinished() {
        val action = GuessGameFragmentDirections
            .actionGuessGameFragmentToGuessScoreFragment(viewModel.score.value ?:0)
        findNavController(this).navigate(action)
        Toast.makeText(requireContext(), "Finshed", Toast.LENGTH_SHORT).show()
    }
}
