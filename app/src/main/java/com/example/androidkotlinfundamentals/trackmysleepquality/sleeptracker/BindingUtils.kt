package com.example.androidkotlinfundamentals.trackmysleepquality.sleeptracker

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.convertDurationToFormatted
import com.example.androidkotlinfundamentals.convertNumericQualityToString
import com.example.androidkotlinfundamentals.trackmysleepquality.database.SleepNight

@BindingAdapter("sleepDurationFormatted")
fun TextView.setSleepDuration(night: SleepNight?) {
    night?.let {
        text = convertDurationToFormatted(
            night.startTimeMillis,
            night.endTimeMillis,
            context.resources
        )
    }
}
@BindingAdapter("sleepQuality")
fun TextView.setSleepQualityString(night: SleepNight?) {
    night?.let {
        text = convertNumericQualityToString(night.sleepQuality, context.resources)
    }
}
@BindingAdapter("sleepImage")
fun ImageView.setSleepImage(night: SleepNight?) {
    night?.let {
        setImageResource(
            when (night.sleepQuality) {
                0 -> R.drawable.ic_sleep_0
                1 -> R.drawable.ic_sleep_1
                2 -> R.drawable.ic_sleep_2
                3 -> R.drawable.ic_sleep_3
                4 -> R.drawable.ic_sleep_4
                5 -> R.drawable.ic_sleep_5
                else -> R.drawable.ic_sleep_active
            }
        )
    }
}
