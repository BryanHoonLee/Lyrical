package hoonstudio.com.tutory.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.roomdb.entity.Song
import kotlinx.android.synthetic.main.item_recording.view.*
import kotlinx.android.synthetic.main.item_song.view.*
import kotlinx.android.synthetic.main.item_song.view.image_view_song_art
import kotlinx.android.synthetic.main.item_song.view.text_view_artist
import kotlinx.android.synthetic.main.item_song.view.text_view_title

class RecordingsAdapter(onRecordingsItemClickListener: OnRecordingsItemClickListener) :
    RecyclerView.Adapter<RecordingsAdapter.RecordingsViewHolder>() {
    private var recordingsList = emptyList<Song>()
    private var onRecordingsItemClickListener: OnRecordingsItemClickListener

    private var expandedPosition = RecyclerView.NO_POSITION

    init {
        this.onRecordingsItemClickListener = onRecordingsItemClickListener
    }

    fun setRecordingsList(recordingsList: List<Song>) {
        this.recordingsList = recordingsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordingsViewHolder {
        var itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.item_recording, parent, false)

        return RecordingsViewHolder(itemView, onRecordingsItemClickListener)
    }

    override fun getItemCount(): Int {
        return recordingsList.size
    }

    fun getRecordingAt(position: Int): Song {
        return recordingsList.get(position)
    }

    override fun onBindViewHolder(holder: RecordingsViewHolder, position: Int) {
        var current = recordingsList.get(position)
        var imageArtUrl = current.songArtImageUrl
        holder.textViewArtist.text = current.primaryArtist.name
        holder.textViewAudioName.text = current.audioName
        holder.textViewTitle.text = current.title

        Picasso.get()
            .load(imageArtUrl)
            // .resize(350,350)
            .into(holder.imageViewSongArt)
    }

    class RecordingsViewHolder(itemView: View, onRecordingsItemClickListener: OnRecordingsItemClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var textViewAudioName = itemView.text_view_audio_name
        var textViewTitle = itemView.text_view_title
        var textViewArtist = itemView.text_view_artist
        var imageViewSongArt = itemView.image_view_song_art

        var onRecordingsItemClickListener: OnRecordingsItemClickListener

        init {
            itemView.setOnClickListener(this)
            this.onRecordingsItemClickListener = onRecordingsItemClickListener
        }

        override fun onClick(v: View?) {
            onRecordingsItemClickListener.onRecordingsItemClick(adapterPosition)
        }
    }

    interface OnRecordingsItemClickListener {
        fun onRecordingsItemClick(position: Int)
    }
}