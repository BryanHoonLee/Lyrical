package hoonstudio.com.tutory

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso
import hoonstudio.com.tutory.data.viewmodel.SharedRecordingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_play_recording.*
import kotlinx.android.synthetic.main.snippet_audio_player.*
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"

class PlayRecordingFragment : Fragment() {
    private var player: MediaPlayer? = null
    private lateinit var sharedRecordingViewModel: SharedRecordingViewModel
    private var length: Int? = null
    private var handler = Handler()
    private lateinit var ticker: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_play_recording, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedRecordingViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedRecordingViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        sharedRecordingViewModel.sharedRecording.observe(viewLifecycleOwner, Observer { song ->
            Picasso
                .get()
                .load(song.songArtImageUrl)
                .into(image_view_song_art)

            loadPlayer(song.filePath)

            player?.let { mediaPlayer ->
                mediaPlayer.setOnCompletionListener {
                    if (button_play != null && button_play.isChecked) {
                        seekBar.progress = 0
                        button_play.toggle()
                    }
                    handler.removeCallbacks(ticker)
                }
            }

            button_play?.let { button ->
                button.setOnCheckedChangeListener { buttonView, isChecked ->
                    if (isChecked) {
                        player?.let { mediaPlayer ->
                            if (!mediaPlayer.isPlaying && mediaPlayer.currentPosition == 0) {
                                startPlaying(0)
                            } else {
                                resumeAudio()
                            }
                        }
                    } else {
                        pauseAudio()
                    }
                }
            }
        })

        ticker = Runnable {
            player?.let { mediaPlayer ->
                var currentPosition = mediaPlayer.currentPosition / 1000
                seekBar.setProgress(currentPosition)
                Log.d("eeee", "onActivityCreated: yikes")
                handler.postDelayed(ticker, 500)
            }
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    player?.let {mediaPlayer ->
                        if(!mediaPlayer.isPlaying){
                            button_play.toggle()
                            startPlaying(progress * 1000)
                        }else{
                            mediaPlayer.seekTo(progress * 1000)
                        }
                    }
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
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

    private fun loadPlayer(filePath: String) {
        player = MediaPlayer().apply {
            try {
                reset()
                setDataSource(filePath)
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }

        seekBar.max = player?.let {
            it.duration / 1000
        } ?: 0
    }

    private fun startPlaying(time: Int) {
        player?.let { mediaPlayer ->
            mediaPlayer.seekTo(time)
            Log.d("eeeee", "$time")
            mediaPlayer.start()
        }
        handler.postDelayed(ticker, 500)

    }

    private fun releaseMediaPlayer() {
        try {
            player?.let {
                if (it.isPlaying) {
                    it.stop()
                    it.release()
                    player = null
                } else {
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

    override fun onDestroy() {
        super.onDestroy()
        releaseMediaPlayer()
    }
}