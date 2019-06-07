package hoonstudio.com.tutory.data.RoomDB

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.R
import kotlinx.android.synthetic.main.recyclerview_item_song_name.view.*

class SongListAdapter internal constructor(
    context: Context
): RecyclerView.Adapter<SongListAdapter.SongListViewHolder>(){

    private val inflater:LayoutInflater = LayoutInflater.from(context)
    private var songs = emptyList<Song>()

    inner class SongListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var songItemView : TextView = itemView.findViewById(R.id.songNameTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongListViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item_song_name, parent, false)
        Log.d("onCreateViewHolder: ", "test")
        return SongListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongListViewHolder, position: Int) {
        val current = songs[position]
        Log.d("onBindViewHolder: ", holder.songItemView.text.toString())
        holder.songItemView.text = current.songName
        Log.d("onBindViewHolder: ", holder.songItemView.text.toString())
    }

    internal fun setSongs(songs: List<Song>){
        this.songs = songs
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        Log.d("getItemCount: ", songs.size.toString())
        return songs.size
    }


}