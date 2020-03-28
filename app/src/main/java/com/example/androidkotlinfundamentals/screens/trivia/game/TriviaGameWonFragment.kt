package com.example.androidkotlinfundamentals.screens.trivia.game

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.androidkotlinfundamentals.R
import com.example.androidkotlinfundamentals.databinding.FragmentTriviaGameWonBinding

class TriviaGameWonFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTriviaGameWonBinding>(inflater,R.layout.fragment_trivia_game_won,container,false)
        binding.nextMatchButton.setOnClickListener {
            Navigation.createNavigateOnClickListener(  R.id.action_gameWonFragment_to_gameFragment )
        }
        setHasOptionsMenu(true)
        return binding.root
    }
    private fun getShareIntent(): Intent {
        val args = arguments?.let {
            TriviaGameWonFragmentArgs.fromBundle(
                it
            )
        }
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.type = "text/plain"
//        shareIntent.putExtra(Intent.EXTRA_TEXT,getString(R.string.share_success_text,args?.numCorrect,args?.numQuestions))
        return ShareCompat
                .IntentBuilder
                .from(requireActivity())
                .setText(getString(R.string.share_success_text,args?.numCorrect,args?.numQuestions))
                .setType("text/plain")
                .intent
    }
    private fun shareSuccess(){
        startActivity(getShareIntent())
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu,menu)
        menu.findItem(R.id.share).isVisible = getShareIntent().resolveActivity(activity?.packageManager)?.let { true }?:false

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.share) shareSuccess()
        return true
    }
}
