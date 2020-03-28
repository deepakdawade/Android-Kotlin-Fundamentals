package com.example.androidkotlinfundamentals.marsrealestate.marsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.example.androidkotlinfundamentals.databinding.FragmentMarsDetailBinding

class MarsDetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {  val application = requireNotNull(activity).application
        val binding = FragmentMarsDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val marsProperty = MarsDetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = MarsDetailViewModelFactory(marsProperty, application)
        binding.viewModel = ViewModelProviders.of(
            this, viewModelFactory).get(MarsDetailViewModel::class.java)
        return binding.root
    }
}