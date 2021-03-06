package com.example.androidkotlinfundamentals.trackmysleepquality.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentSleepQualityBinding
import com.example.androidkotlinfundamentals.trackmysleepquality.database.SleepDatabase

class SleepQualityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(inflater ,R.layout.fragment_sleep_quality, container, false)
        val arguments = SleepQualityFragmentArgs.fromBundle(arguments!!)
        val application = requireNotNull(this.activity).application
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey, dataSource)
        val sleepQualityViewModel =
            ViewModelProviders.of(
                this, viewModelFactory).get(SleepQualityViewModel::class.java)
        binding.sleepQualityViewModel = sleepQualityViewModel
        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner,  Observer {
            if (it == true) { // Observed state is true.
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment())
                sleepQualityViewModel.doneNavigating()
            }
        })
        return binding.root
    }
}
