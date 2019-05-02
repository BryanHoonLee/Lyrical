package hoonstudio.com.tutory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import hoonstudio.com.tutory.RoomDB.User
import hoonstudio.com.tutory.RoomDB.UserViewModel


class RegistrationActivity : AppCompatActivity(){

    private lateinit var userViewModel: UserViewModel
    private lateinit var registrationUsernameEditText: EditText
    private lateinit var registrationEmailEditText: EditText
    private lateinit var registrationPasswordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Log.d("RegistrationActivity: ", "brOOOOOO START WTF")

        registrationUsernameEditText = findViewById<EditText>(R.id.registrationUsernameEditText)
        registrationEmailEditText = findViewById<EditText>(R.id.registrationEmailEditText)
        registrationPasswordEditText = findViewById<EditText>(R.id.registrationPasswordEditText)
        Log.d("RegistrationActivity: ", "YOOOOOOOOOO START WTF")


        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        val registrationAreadyHaveAnAccountTextView = findViewById<TextView>(R.id.registrationAlreadyHaveAnAccountTextView)
        registrationAreadyHaveAnAccountTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent);
        }

        val registrationButton = findViewById<Button>(R.id.registrationButton)
        registrationButton.setOnClickListener {
            var username = registrationUsernameEditText.text.toString()
            Log.d("RegistrationActivity: ", username)
            var email = registrationEmailEditText.text.toString()
            var password = registrationPasswordEditText.text.toString()
            val user = User(null, username, email, password, null, null, null, null, null)
            Log.d("RegistrationActivity2: ", username)
            userViewModel.insert(user)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(EXTRA_USERNAME, username)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent);
        }
    }

    companion object{
        const val EXTRA_USERNAME = "RegistrationActivity.USERNAME"
    }
}