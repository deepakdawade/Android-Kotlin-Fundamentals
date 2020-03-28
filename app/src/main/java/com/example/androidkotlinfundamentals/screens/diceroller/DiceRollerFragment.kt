package com.example.androidkotlinfundamentals.screens.diceroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentDiceRollerBinding

class DiceRollerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding:FragmentDiceRollerBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_dice_roller,container,false)
        val viewModel = ViewModelProviders.of(this).get(DiceRollerViewModel::class.java)
        val textObserver = Observer<Int> {
            binding.resultTextView.text = if(it !in 1..6) {
                binding.countUpButton.isEnabled = false
                "Dice Rolled!"
            } else {
                binding.countUpButton.isEnabled = true
                it.toString()
            }
            val drawableResource = when(it){
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.empty_dice
            }
            binding.diceImageView.setImageResource(drawableResource)
        }
        viewModel.textCounter.observe(viewLifecycleOwner, textObserver)

        binding.rollButton.setOnClickListener {
            viewModel.generateRandomNumber()
        }
        binding.countUpButton.setOnClickListener {
            viewModel.countUp()
        }
        return binding.root
    }
}