package hoonstudio.com.tutory.ui

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.network.response.Hit
import hoonstudio.com.tutory.data.viewmodel.SharedHitViewModel
import kotlinx.android.synthetic.main.fragment_lyric_recording.*
import kotlinx.android.synthetic.main.snippet_recorder.*
import java.io.File
import java.io.IOException
import java.util.*

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class LyricRecordFragment : Fragment() {
    private lateinit var sharedHitViewModel: SharedHitViewModel

    private lateinit var toast: Toast

    private var recorder: MediaRecorder? = null

    private var filePath: String = ""

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lyric_recording, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       // filePath = "${Environment.getExternalStorageDirectory()}/audiorecordtest.3gp"

        sharedHitViewModel = activity?.let{
            ViewModelProviders.of(it).get(SharedHitViewModel::class.java)
        } ?: throw Exception("Invalid Class")

        sharedHitViewModel.sharedHit.observe(this, androidx.lifecycle.Observer {
            loadWebView(it.result.url)
        })

        button_record.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Start recording

                // Brings up dialog, asking user for record permissions if not granted already
                ActivityCompat.requestPermissions(activity!!, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
                // Since this is a dangerous permission, checks everytime if record permission is granted before
                // audio recording starts when user presses record toggle button
                if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.RECORD_AUDIO)
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    // If permission is not granted
                    buttonView.toggle()
                } else {
                    // If permission is granted
                    button_save_recording.visibility = View.VISIBLE
                    if (recorder == null) {
                        startRecording()
                    } else {
                        resumeRecording()
                    }
                    showToast("Recording Started")
                }
            } else if (!isChecked) {
                // Recording gets paused.
                pauseRecording()
                showToast("Recording Paused")
            }
        }

        button_save_recording.setOnClickListener(View.OnClickListener {
            stopRecording()
        })
    }

    private fun startRecording() {
        // apply lets you use all the functions without typing out the source variable each time.
        var audioName = Calendar.getInstance().time.toString().replace(" ", "").replace(":", "")
        var test = "test"
        filePath = "${Environment.getExternalStorageDirectory().absoluteFile}${File.separator}$test.3gp"
        recorder = MediaRecorder().apply {
            reset()
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(filePath)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
                Log.e(LOG_TAG, filePath)
            }
            start()
        }
    }

    private fun resumeRecording() {
        recorder?.resume()
    }

    private fun pauseRecording() {
        recorder?.pause()
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }

    }

    fun loadWebView(url: String) {
        if (url != null) {
            showToast(url)
            webView.loadUrl(url)
            // Starts scrollbar in webview down in order to start screen at lyrics.
            webView.scrollY = 1000
        }else{
            showToast("URL Not Loaded")
        }
    }

    fun showToast(message: String) {
        if (::toast.isInitialized && toast != null) {
            toast.cancel()
        }
        toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onStop() {
        super.onStop()
        recorder?.release()
        recorder = null
    }

    companion object {
        fun newInstance(): LyricRecordFragment = LyricRecordFragment()
    }
}