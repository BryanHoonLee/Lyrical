package hoonstudio.com.tutory.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.widget.DialogTitle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import hoonstudio.com.tutory.R
import kotlinx.android.synthetic.main.fragment_lyric_recording.*
import kotlinx.android.synthetic.main.snippet_recorder.*

private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class LyricRecordFragment : Fragment() {
    private lateinit var songTitle: String
    private lateinit var songArtist: String
    private lateinit var songArtUrl: String
    private lateinit var lyricUrl: String
    private lateinit var toast: Toast

    // Requesting permission to RECORD_AUDIO
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_lyric_recording, container, false)

        songTitle = arguments!!.getString("title")
        songArtist = arguments!!.getString("artist")
        songArtUrl = arguments!!.getString("songArtUrl")
        lyricUrl = arguments!!.getString("lyricUrl")


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadWebView(lyricUrl)

        button_record.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Start recording

                // Brings up dialog, asking user for record permissions if not granted already
                ActivityCompat.requestPermissions(activity!!, permissions, REQUEST_RECORD_AUDIO_PERMISSION)
                // Since this is a dangerous permission, checks everytime if record permission is granted before
                // audio recording starts when user presses record toggle button
                if (ContextCompat.checkSelfPermission(activity!!, Manifest.permission.RECORD_AUDIO )
                    != PackageManager.PERMISSION_GRANTED) {
                    // If permission is not granted
                    buttonView.toggle()
                }else{
                    // If permission is granted
                    button_save_recording.visibility = View.VISIBLE
                    showToast("Recording Started")
                }
            } else if (!isChecked) {
                // Recording gets paused.
                showToast("Recording Paused")
            }
        }
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
            webView.loadUrl(url)
            // Starts scrollbar in webview down in order to start screen at lyrics.
            webView.scrollY = 1000
        }
    }

    fun showToast(message: String) {
        if (::toast.isInitialized && toast != null) {
            toast.cancel()
        }
        toast = Toast.makeText(activity, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    companion object {
        fun newInstance(): LyricRecordFragment = LyricRecordFragment()
    }
}