package hoonstudio.com.tutory.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.network.response.Hit
import hoonstudio.com.tutory.data.network.response.Result
import hoonstudio.com.tutory.data.roomdb.entity.Search
import hoonstudio.com.tutory.data.viewmodel.SearchHistoryViewModel
import hoonstudio.com.tutory.data.viewmodel.SharedHitViewModel
import hoonstudio.com.tutory.data.viewmodel.SongViewModel
import hoonstudio.com.tutory.ui.adapter.SearchAdapter
import hoonstudio.com.tutory.ui.adapter.SearchHistoryAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchAdapter.OnSearchItemClickListener,
    SearchHistoryAdapter.OnSearchHistoryItemClickListener {
    private lateinit var songViewModel: SongViewModel
    private lateinit var searchViewModel: SearchHistoryViewModel
    private lateinit var sharedSongViewModel: SharedHitViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var searchHistoryAdapter: SearchHistoryAdapter
    private lateinit var songList: List<Hit>
    private lateinit var searchHistoryList: List<Search>

    // Creates the view hierarchy controlled by the fragment
    // Activities use setContentView() to specify the XML file that defines their layouts, but
    // fragments create their view hierarchy in onCreateView()
    // Fragments don't need onCreate() since they are attached to an Activity that calls it
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        initRecyclerView(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)
        searchViewModel = ViewModelProviders.of(this).get(SearchHistoryViewModel::class.java)

        sharedSongViewModel = activity?.run {
            ViewModelProviders.of(this).get(SharedHitViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        searchViewModel.searchHistoryList.observe(this, Observer {
            searchHistoryAdapter.setSearchHistoryList(it)
            searchHistoryList = it

        })
        searchViewModel.getSearchHistoryFromDb()

        songViewModel.songQuery.observe(this, Observer {
            adapter.setSongList(it.response.hits)
            songList = it.response.hits
        })

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
      //          if (!(recyclerView.adapter is SearchAdapter)) {
                    recyclerView.adapter = adapter
              //  }
                songViewModel.getSong(query ?: "")
                searchLabel.text = "Search Results"
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onSearchItemClick(position: Int) {
        sharedSongViewModel.setSharedSong(songList.get(position))
        var timeStamp = System.currentTimeMillis()
        songList.get(position).result.apply {
            var search = Search(
                annotationCount,
                apiPath,
                fullTitle,
                headerImageThumbnailUrl,
                headerImageUrl,
                id,
                timeStamp,
                lyricsOwnerId,
                lyricsState,
                path,
                primaryArtist,
                songArtImageThumbnailUrl,
                songArtImageUrl,
                title,
                titleWithFeatured,
                url
            )

            searchViewModel.insertSearch(search)
        }

        val fragment = LyricRecordFragment.newInstance()
        startFragment(fragment)
    }

    override fun onSearchHistoryItemClickListener(position: Int) {
        searchHistoryList.get(position).apply {
            var hit: Hit
            var result = Result(
                annotationCount,
                apiPath,
                fullTitle,
                headerImageThumbnailUrl,
                headerImageUrl,
                id,
                lyricsOwnerId,
                lyricsState,
                path,
                primaryArtist,
                songArtImageThumbnailUrl,
                songArtImageUrl,
                title,
                titleWithFeatured,
                url
            )
            hit = Hit(result)
            sharedSongViewModel.setSharedSong(hit)
        }
        val fragment = LyricRecordFragment.newInstance()
        startFragment(fragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var activity = context as MainActivity
        activity.bottomNavigation.visibility = View.VISIBLE
    }

    override fun onResume() {
        super.onResume()
        var activity = context as MainActivity
        activity.bottomNavigation.visibility = View.VISIBLE
    }

    private fun startFragment(fragment: Fragment) {
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)

        adapter = SearchAdapter(this)
        searchHistoryAdapter = SearchHistoryAdapter(this)

        recyclerView.adapter = searchHistoryAdapter


    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}