package hoonstudio.com.tutory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_welcome_page_profile.*

class WelcomePageProfileFragment: Fragment(){

    companion object{
        fun newInstance(): WelcomePageProfileFragment{
            return WelcomePageProfileFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_welcome_page_profile, container, false)
        val nextButton: Button = view.findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val intent = Intent(activity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return view
    }
}