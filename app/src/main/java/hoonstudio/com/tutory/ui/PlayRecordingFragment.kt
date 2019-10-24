package hoonstudio.com.tutory.ui

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.roomdb.entity.Song
import hoonstudio.com.tutory.data.viewmodel.SharedRecordingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_recording.*
import kotlinx.android.synthetic.main.fragment_play_recording.view.*
import kotlinx.android.synthetic.main.snippet_audio_player.*
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"

class PlayRecordingFragment : Fragment() {
    private var player: MediaPlayer? = null
    private lateinit var sharedRecordingViewModel: SharedRecordingViewModel
    private var recording: Song? = null
    private var length: Int? = null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_play_recording, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedRecordingViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedRecordingViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        sharedRecordingViewModel.sharedRecording.observe(this, Observer {
            recording = it
            Picasso
                .get()
                .load(it.songArtImageUrl)
                .into(image_view_song_art)

            button_play.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    if(player == null){
                        startPlaying(it.filePath)
                    }else{
                        resumeAudio()
                    }
                } else {
                    pauseAudio()
                }
            }
        })
    }

    private fun pauseAudio() {
        player?.let {
            it.pause()
            length = it.currentPosition
        }
    }

    private fun resumeAudio() {
        player?.let {
            it.seekTo(length ?: 0)
            it.start()
        }
    }

    private fun startPlaying(filePath: String) {
        releaseMediaPlayer()
        player = MediaPlayer().apply {
            try {
                reset()
                setDataSource(filePath)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
        player?.let {
            it.setOnCompletionListener {
                if(button_play.isChecked){
                    button_play.toggle()
                }
                releaseMediaPlayer()
            }
        }
    }

    private fun releaseMediaPlayer() {
        try {
            player?.let {
                if (it.isPlaying) {
                    it.stop()
                    it.release()
                    player = null
                }else{
                    it.release()
                    player = null
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var activity = context as MainActivity
        activity.bottomNavigation.visibility = View.GONE
    }

    companion object {
        fun newInstance(): PlayRecordingFragment = PlayRecordingFragment()
    }
}