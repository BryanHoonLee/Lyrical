package hoonstudio.com.tutory

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hoonstudio.com.tutory.data.network.ConnectivityInterceptorImpl
import hoonstudio.com.tutory.data.network.GeniusApiService
import hoonstudio.com.tutory.data.network.SongNetworkDataSourceImpl
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        return view
    }

//    fun openWebPage(url: String) {
//        val webpage: Uri = Uri.parse(url)
//        val intent = Intent(Intent.ACTION_VIEW, webpage)
//        if (intent.resolveActivity(activity?.packageManager) != null) {
//            startActivity(intent)
//        }
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}