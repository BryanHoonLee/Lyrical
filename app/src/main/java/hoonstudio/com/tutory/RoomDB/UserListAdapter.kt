package hoonstudio.com.tutory.RoomDB

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hoonstudio.com.tutory.R

class UserListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>(){

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var userItemView: TextView = itemView.findViewById(R.id.user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.user_recyclerview_item, parent, false)
        Log.d("onCreateViewHolder: ", "test")
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int){
        val current = users[position]
        Log.d("onBindViewHolder: ", holder.userItemView.text.toString())
        holder.userItemView.text = current.username
    }

    internal fun setUsers(users: List<User>){
        this.users = users
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int{
        Log.d("getItemCount: ", users.size.toString())
        return users.size;
    }
    
}