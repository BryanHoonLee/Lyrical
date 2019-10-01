package hoonstudio.com.tutory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.data.network.ConnectivityInterceptorImpl
import hoonstudio.com.tutory.data.network.GeniusApiService
import hoonstudio.com.tutory.data.network.SongNetworkDataSourceImpl
import hoonstudio.com.tutory.data.network.response.SearchResponse
import hoonstudio.com.tutory.data.viewmodel.SongViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private val scope = CoroutineScope(Dispatchers.Default)
    private lateinit var songViewModel: SongViewModel

    // Creates the view hierarchy controlled by the fragment
    // Activities use setContentView() to specify the XML file that defines their layouts, but
    // fragments create their view hierarchy in onCreateView()
    // Fragments don't need onCreate() since they are attached to an Activity that calls it
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        songViewModel = ViewModelProviders.of(this).get(SongViewModel::class.java)

//        val apiService = GeniusApiService(ConnectivityInterceptorImpl(this.context!!))
//        val songNetworkDataSource = SongNetworkDataSourceImpl(apiService)
//        songNetworkDataSource.song.observe(this, Observer {
//            webView.loadUrl(it.response.hits[0].result.url)
//        })
//        GlobalScope.launch(Dispatchers.Main) {
//            songNetworkDataSource.fetchSong(query?:"Dancing Mellow Fellow")
//        }

        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launchWhenCreated {
                    songViewModel.getSong(query ?: "Dancing Mellow Fellow").observe(this@SearchFragment, Observer {

                    })
                }


                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    companion object {
        fun newInstance(): SearchFragment = SearchFragment()
    }
}