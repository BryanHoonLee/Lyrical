package hoonstudio.com.tutory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class HomeFragment : Fragment(){

    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        fab.setOnClickListener {
            Log.d("HomeFragment", "Clicked FAB - Start NewUserActivity")
            val intent = Intent(activity, NewUserActivity::class.java)
            startActivityForResult(intent, newUserActivityRequstCode)
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // move this stuff to onActivitycreated
        val recyclerView = activity?.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = UserListAdapter(activity!!.applicationContext)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(activity!!.applicationContext)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer{ users ->
            users?.let{ adapter.setUsers(it)}
        })


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