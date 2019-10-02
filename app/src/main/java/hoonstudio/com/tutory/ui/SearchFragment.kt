package hoonstudio.com.tutory.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.R
import hoonstudio.com.tutory.data.viewmodel.SongViewModel
import hoonstudio.com.tutory.ui.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class SearchFragment : Fragment() {
    private lateinit var songViewModel: SongViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter

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


        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launchWhenCreated {
                    songViewModel.getSong(query ?: "Dancing Mellow Fellow").observe(this@SearchFragment, Observer {
                        adapter.setSongList(it.response.hits)
                    })
                }


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)

//        val apiService = GeniusApiService(ConnectivityInterceptorImpl(this.context!!))
//        val songNetworkDataSource = SongNetworkDataSourceImpl(apiService)
//        songNetworkDataSource.song.observe(this, Observer {
//            webView.loadUrl(it.response.hits[0].result.url)
//        })
//        GlobalScope.launch(Dispatchers.Main) {
//            songNetworkDataSource.fetchSong(query?:"Dancing Mellow Fellow")
//        }


    }

    private fun initRecyclerView(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        recyclerView.setHasFixedSize(true)

        adapter = SearchAdapter()
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}