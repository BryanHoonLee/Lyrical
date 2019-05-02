package hoonstudio.com.tutory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hoonstudio.com.tutory.data.MusixmatchLyricApiService
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment: Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val apiService = MusixmatchLyricApiService()

        // temporary bad practice
        GlobalScope.launch(Dispatchers.Main){
            val currentLyrics = apiService.getLyrics("15953433").await()
            val lyricTextView = lyricTextView
            lyricTextView.setText(currentLyrics.message.body.lyrics.lyricsBody.toString())
        }

        return view
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}