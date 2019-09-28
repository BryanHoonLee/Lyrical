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
import hoonstudio.com.tutory.data.RoomDB.User
import hoonstudio.com.tutory.data.RoomDB.UserListAdapter
import hoonstudio.com.tutory.data.RoomDB.UserViewModel
import hoonstudio.com.tutory.data.network.GeniusApiService
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(){

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        val webView = view.findViewById<WebView>(R.id.webView)

        fab.setOnClickListener {
            Log.d("HomeFragment", "Clicked FAB - Start NewUserActivity")
//            val intent = Intent(activity, NewUserActivity::class.java)
//            startActivityForResult(intent, newUserActivityRequstCode)
            webView.loadUrl("https://genius.com/Justin-bieber-love-yourself-lyrics")

            webView.webViewClient = object : WebViewClient() {
                override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                    Log.i("WEB_VIEW_TEST", "error code:$errorCode")
                    super.onReceivedError(view, errorCode, description, failingUrl)
                }
            }
        }

        return view
    }

    fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(activity?.packageManager) != null) {
            startActivity(intent)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = UserListAdapter(activity!!.applicationContext)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer{ users ->
            users?.let{ adapter.setUsers(it)}
        })

        val apiService = GeniusApiService()
        GlobalScope.launch(Dispatchers.Main) {
            val currentResponse = apiService.getSong("Sunday Morning").await()

            webView.loadUrl(currentResponse.response.hits[0].result.url)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == newUserActivityRequstCode && resultCode == Activity.RESULT_OK){
            data?.let{
                val user = User(null, it.getStringExtra(NewUserActivity.EXTRA_REPLY), "test", "test", null, null, null, null, null)
                userViewModel.insert(user)
                Toast.makeText(context, "Added", Toast.LENGTH_LONG).show()
            }
        } else{
            Toast.makeText(context, "Not saved", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
        const val newUserActivityRequstCode = 1
    }
}