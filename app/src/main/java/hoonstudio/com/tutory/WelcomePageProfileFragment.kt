package hoonstudio.com.tutory

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import hoonstudio.com.tutory.data.RoomDB.UserViewModel
import kotlinx.android.synthetic.main.fragment_welcome_page_profile.*

class WelcomePageProfileFragment: Fragment(){

    private lateinit var userViewModel : UserViewModel

    companion object{
        fun newInstance(): WelcomePageProfileFragment{
            return WelcomePageProfileFragment()
        }
        const val EXTRA_USERNAME = "WelcomePageProfileFragment.USERNAME"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_welcome_page_profile, container, false)
        val nextButton: Button = view.findViewById(R.id.nextButton)

        val address = addressEditText.text.toString()
        val state = stateEditText.text.toString()
        val city = cityEditText.text.toString()
        val zipcode = zipCodeEditText.text.toString()
        val phoneNumber = phoneNumberEditText.text.toString()

        val usernameExtra = activity?.intent?.getStringExtra(RegistrationActivity.EXTRA_USERNAME)
        val user = userViewModel.getUser(usernameExtra!!)
        user.address = address
        user.state = state
        user.city = city
        user.zipcode = zipcode
        user.phoneNumber = phoneNumber

        nextButton.setOnClickListener {
            userViewModel.updateUser(user)
            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, user.username)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        return view
    }
}