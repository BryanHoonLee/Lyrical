package hoonstudio.com.tutory.ui

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hoonstudio.com.tutory.R
import kotlinx.android.synthetic.main.fragment_recordings.*
import java.io.File
import java.io.IOException

private const val LOG_TAG = "AudioRecordTest"

class RecordingsFragment: Fragment(){
    private var player:MediaPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recordings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        play_recording.setOnClickListener(View.OnClickListener {
            startPlaying()
        })
    }

    private fun startPlaying(){
        var test = "test"
        var filePath = "${Environment.getExternalStorageDirectory().absoluteFile}${File.separator}$test.3gp"
        player = MediaPlayer().apply {
            try{
                setDataSource(filePath)
                prepare()
                start()
            }catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    companion object{
        fun newInstance(): RecordingsFragment = RecordingsFragment()
    }
}