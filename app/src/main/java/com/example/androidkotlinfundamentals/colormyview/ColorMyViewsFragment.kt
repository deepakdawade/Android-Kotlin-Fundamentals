package com.example.androidkotlinfundamentals.colormyview

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentColorMyViewsBinding


class ColorMyViewsFragment : Fragment() {
    lateinit var binding: FragmentColorMyViewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_color_my_views, container, false)
        setListners()
        return binding.root
    }

    private fun makeColored(view: View) {
        when (view.id) {
            binding.boxOne.id -> view.setBackgroundColor(Color.DKGRAY)
            binding.boxTwo.id -> view.setBackgroundColor(Color.GRAY)
//            boxThree.id -> view.setBackgroundColor(Color.BLUE)
//            boxFour.id -> view.setBackgroundColor(Color.MAGENTA)
//            boxFive.id -> view.setBackgroundColor(Color.BLUE)
            binding.redButton.id -> binding.boxThree.setBackgroundResource(R.color.red)
            binding.yellowButton.id -> binding.boxFour.setBackgroundResource(R.color.yellow)
            binding.greenButton.id -> binding.boxFive.setBackgroundResource(R.color.green)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }

    private fun setListners() {
        val clickableViews = listOf<View>(
            binding.boxOne,
            binding.boxTwo,
            binding.boxThree,
            binding.boxFour,
            binding.boxFive,
            binding.constraintLayout,
            binding.redButton,
            binding.yellowButton,
            binding.greenButton
        )
        clickableViews.forEach { view -> view.setOnClickListener { makeColored(it) } }
    }
}
