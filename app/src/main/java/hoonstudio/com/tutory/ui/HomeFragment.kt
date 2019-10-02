package hoonstudio.com.tutory.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hoonstudio.com.tutory.R

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