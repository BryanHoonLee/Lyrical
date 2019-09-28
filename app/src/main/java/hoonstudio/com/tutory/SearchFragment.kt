package hoonstudio.com.tutory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment: Fragment(){

    private lateinit var songViewModel: SongViewModel
    private val scope = CoroutineScope(Dispatchers.Default)
    lateinit var adapter: SongListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)



        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView)
        adapter = SongListAdapter(activity!!.applicationContext)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity!!.applicationContext)


        //problem with this coroutine call. Must recreate activity for it to exit this coroutine and make calls to SongListAdapter


        //retrieveSong()

        Log.d("onActivityCreated: ", "Exit Coroutine")
        //adapter.setSongs(randSongList)



    }
//
//    fun retrieveSong() = scope.launch{
//        val apiService = MusixmatchLyricApiService(ConnectivityInterceptorImpl(activity!!.applicationContext))
//        val randSongList = ArrayList<Song>()
//
//        for (x in 0..2) {
//            val rand = (15000000..15953433).random()
//            // val rand = 15953433
//            val randSong = apiService.getSong("15953433").await()
//            Log.d("onActivityCreated: ", randSong.message.body.track.trackName.toString())
//            val randLyrics = apiService.getLyrics("15953433").await()
//            randSongList.add(
//                Song(
//                    rand,
//                    randSong.message.body.track.trackName.toString(),
//                    randLyrics.message.body.lyrics.lyricsBody.toString()
//                )
//            )
//        }
//
//        initViewModel()
//    }
    suspend fun initViewModel(){
        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        songViewModel.allSongs.observe(this, Observer{ songs ->
            //songs?.let { adapter.setSongs(it)}
            Log.d("onActivityCraeted: ", "adapter.setSong")
            songs?.let { adapter.setSongs(it)}
        })
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}