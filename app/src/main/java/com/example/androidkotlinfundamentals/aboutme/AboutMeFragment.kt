package com.example.androidkotlinfundamentals.aboutme

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentAboutMeBinding
import com.example.androidkotlinfundamentals.aboutme.models.MyName

class AboutMeFragment : Fragment() {
    lateinit var binding: FragmentAboutMeBinding
    private val myName: MyName = MyName("Aleks Haecky")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_about_me,container,false)
//        setContentView(R.layout.activity_about_me)
        binding.doneButton.setOnClickListener {
            addNickname()
        }
        binding.myName = myName 
       return binding.root
    }

    private fun addNickname() {
        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            nicknameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
            invalidateAll()
            // Hide the keyboard.
            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(root.windowToken, 0)
        }
    }
}