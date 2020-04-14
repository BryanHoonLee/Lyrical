package hoonstudio.com.tutory.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.network.response.Hit

class SearchAdapter(
    onSearchItemClickListener: OnSearchItemClickListener
): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    private var songList = emptyList<Hit>()
    private var onSearchItemClickListener: OnSearchItemClickListener

    init{
        this.onSearchItemClickListener = onSearchItemClickListener
    }

    fun setSongList(songList: List<Hit>){
        this.songList = songList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder{
        var itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)

        return SearchViewHolder(itemView, onSearchItemClickListener)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int){
        var current = songList.get(position)
        var songArtUrl = current.result.songArtImageUrl
        holder.textViewTitle.text = current.result.title
        holder.textViewArtist.text = current.result.primaryArtist.name
        Picasso.get()
            .load(songArtUrl)
            .resize(200,200)
            .centerCrop()
            .into(holder.imageViewSongArt)

    }

    class SearchViewHolder(itemView: View, onSearchItemClickListener: OnSearchItemClickListener):
        RecyclerView.ViewHolder(itemView), View.OnClickListener{
        lateinit var textViewTitle: TextView
        lateinit var textViewArtist: TextView
        lateinit var imageViewSongArt: ImageView

        var onSearchItemClickListener: OnSearchItemClickListener

        init {
            initView()
            itemView.setOnClickListener(this)
            this.onSearchItemClickListener = onSearchItemClickListener
        }

        override fun onClick(v: View?) {
            onSearchItemClickListener.onSearchItemClick(adapterPosition)
        }
        fun initView(){
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewArtist = itemView.findViewById(R.id.text_view_artist)
            imageViewSongArt = itemView.findViewById(R.id.image_view_song_art)

        }
    }

    interface OnSearchItemClickListener{
        fun onSearchItemClick(position: Int)
    }
}