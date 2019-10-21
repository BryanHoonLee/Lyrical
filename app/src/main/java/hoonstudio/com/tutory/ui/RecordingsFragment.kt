package hoonstudio.com.tutory.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.roomdb.entity.Song
import hoonstudio.com.tutory.data.viewmodel.SongViewModel
import hoonstudio.com.tutory.ui.adapter.RecordingsAdapter
import java.io.IOException



private const val LOG_TAG = "AudioRecordTest"

class RecordingsFragment : Fragment(), RecordingsAdapter.OnRecordingsItemClickListener {
    private lateinit var songViewModel: SongViewModel
    private var player: MediaPlayer? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecordingsAdapter
    private lateinit var recordingsList: List<Song>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_recordings, container, false)
        initRecyclerView(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        songViewModel.getAllSongFromDb()

        songViewModel.songList.observe(this, Observer {
            adapter.setRecordingsList(it)
            recordingsList = it
        })

        player?.let {
            it.setOnCompletionListener {
                releaseMediaPlayer()
            }
        }
    }

    override fun onRecordingsItemClick(position: Int) {
        var filePath = recordingsList.get(position).filePath
        startPlaying(filePath)
    }

    private fun startPlaying(filePath: String) {
        releaseMediaPlayer()
        player = MediaPlayer().apply {
            try {
                setDataSource(filePath)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun releaseMediaPlayer() {
        try {
            player?.let {
                if(it.isPlaying){
                    it.stop()
                    it.release()
                    player = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)

        adapter = RecordingsAdapter(this)
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): RecordingsFragment = RecordingsFragment()
    }
}