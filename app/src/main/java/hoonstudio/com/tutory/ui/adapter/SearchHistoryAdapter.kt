package hoonstudio.com.tutory.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.network.response.Hit
import hoonstudio.com.tutory.data.roomdb.entity.Search

class SearchHistoryAdapter(
    onSearchHistoryItemClickListener: OnSearchHistoryItemClickListener
): RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder>(){
    private var searchHistoryList = emptyList<Search>()
    private var onSearchHistoryItemClickListener: OnSearchHistoryItemClickListener

    init{
        this.onSearchHistoryItemClickListener = onSearchHistoryItemClickListener
    }

    fun setSearchHistoryList(searchHistoryList: List<Search>){
        this.searchHistoryList = searchHistoryList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHistoryViewHolder{
        var itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)

        return SearchHistoryViewHolder(itemView, onSearchHistoryItemClickListener)
    }

    override fun getItemCount(): Int {
        return searchHistoryList.size
    }


    override fun onBindViewHolder(holder: SearchHistoryViewHolder, position: Int){
        var current = searchHistoryList.get(position)
        var songArtUrl = current.songArtImageUrl
        holder.textViewTitle.text = current.title
        holder.textViewArtist.text = current.primaryArtist.name
        Picasso.get()
            .load(songArtUrl)
            .resize(200,200)
            .centerCrop()
            .into(holder.imageViewSongArt)

    }

    class SearchHistoryViewHolder(itemView: View, onSearchHistoryItemClickListener: OnSearchHistoryItemClickListener):
        RecyclerView.ViewHolder(itemView), View.OnClickListener{
        lateinit var textViewTitle: TextView
        lateinit var textViewArtist: TextView
        lateinit var imageViewSongArt: ImageView

        var onSearchHistoryItemClickListener: OnSearchHistoryItemClickListener

        init {
            initView()
            itemView.setOnClickListener(this)
            this.onSearchHistoryItemClickListener = onSearchHistoryItemClickListener
        }

        override fun onClick(v: View?) {
            onSearchHistoryItemClickListener.onSearchHistoryItemClickListener(adapterPosition)
        }
        fun initView(){
            textViewTitle = itemView.findViewById(R.id.text_view_title)
            textViewArtist = itemView.findViewById(R.id.text_view_artist)
            imageViewSongArt = itemView.findViewById(R.id.image_view_song_art)

        }
    }

    interface OnSearchHistoryItemClickListener{
        fun onSearchHistoryItemClickListener(position: Int)
    }
}