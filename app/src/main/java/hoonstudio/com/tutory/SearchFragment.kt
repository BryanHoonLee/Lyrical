package hoonstudio.com.tutory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.data.RoomDB.Song
import hoonstudio.com.tutory.data.RoomDB.SongListAdapter
import hoonstudio.com.tutory.data.RoomDB.SongViewModel
import hoonstudio.com.tutory.data.RoomDB.UserViewModel
import hoonstudio.com.tutory.data.network.ConnectivityInterceptorImpl
import hoonstudio.com.tutory.data.network.MusixmatchLyricApiService
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment: Fragment(){

    private lateinit var songViewModel: SongViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

//        val apiService = MusixmatchLyricApiService(ConnectivityInterceptorImpl(this.context!!))
//
//        // temporary bad practice
//        GlobalScope.launch(Dispatchers.Main){
//            val currentLyrics = apiService.getLyrics("15953433").await()
//            val lyricTextView = lyricTextView
//            lyricTextView.setText(currentLyrics.message.body.lyrics.lyricsBody.toString())
//        }


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val apiService = MusixmatchLyricApiService(ConnectivityInterceptorImpl(this.context!!))

        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = SongListAdapter(activity!!.applicationContext)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        val randSongList = ArrayList<Song>()
        GlobalScope.launch(Dispatchers.Main) {
            for (x in 0..2) {
               val rand = (15000000..15953433).random()
               // val rand = 15953433
                val randSong = apiService.getSong(rand.toString()).await()
                Log.d("onActivityCreated: ", randSong.message.body.track.trackName.toString())
                val randLyrics = apiService.getLyrics(rand.toString()).await()
                randSongList.add(
                    Song(
                        rand,
                        randSong.message.body.track.trackName.toString(),
                        randLyrics.message.body.lyrics.lyricsBody.toString()
                    )
                )
            }
        }
      //  adapter.setSongs(randSongList)

        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        songViewModel.allSongs.observe(this, Observer{ songs ->
            //songs?.let { adapter.setSongs(it)}
            songs?.let { adapter.setSongs(randSongList)}
        })

    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}