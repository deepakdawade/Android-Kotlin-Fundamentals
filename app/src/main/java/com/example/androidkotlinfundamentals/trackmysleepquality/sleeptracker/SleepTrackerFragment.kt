package com.example.androidkotlinfundamentals.trackmysleepquality.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentSleepTrackerBinding
import com.example.androidkotlinfundamentals.trackmysleepquality.database.SleepDatabase
import com.google.android.material.snackbar.Snackbar

class SleepTrackerFragment : Fragment() {
    private lateinit var binding: FragmentSleepTrackerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sleep_tracker,container,false)
        val application = requireNotNull(this.activity).application
        val databaseDao = SleepDatabase.getInstance(application).sleepDatabaseDao
        val  sleepTrackerViewModelFactory = SleepTrackerViewModelFactory(databaseDao,application)

        val sleepTrackerViewModel = ViewModelProviders.of(this,sleepTrackerViewModelFactory).get(SleepTrackerViewModel::class.java)
        binding.lifecycleOwner = this
        binding.sleepTrackerViewModel = sleepTrackerViewModel
        sleepTrackerViewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer {
                night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections
                        .actionSleeptrackerFragmentToSleepQualityFragment(night.nightId))
                sleepTrackerViewModel.doneNavigating()
        }})
        sleepTrackerViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it) {
                Snackbar.make(
                    activity!!.findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT // How long to display the message.
                ).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })
        sleepTrackerViewModel.navigateToSleepDataQuality.observe(viewLifecycleOwner, Observer {night ->
            night?.let {
                this.findNavController().navigate(SleepTrackerFragmentDirections
                        .actionSleepTrackerFragmentToSleepDetailFragment(night))
                sleepTrackerViewModel.onSleepDataQualityNavigated()
            }
        })
        val sleepNightAdapter = SleepNightAdapter(SleepNightListener {
            sleepId -> sleepTrackerViewModel.onSleepNightClicked(sleepId)
        })
        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let {
                sleepNightAdapter.addHeaderAndSubmitList(it)
            }
        })
        val manager = GridLayoutManager(activity,3)
        manager.spanSizeLookup = object :GridLayoutManager.SpanSizeLookup(){
            override fun getSpanSize(position: Int): Int = if (position == 0) 3 else 1

        }
        binding.sleepList.layoutManager = manager
        binding.sleepList.adapter = sleepNightAdapter
        return binding.root
    }
}
