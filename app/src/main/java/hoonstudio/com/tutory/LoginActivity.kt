package hoonstudio.com.tutory

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){

    companion object{
        val TAG = "LoginActivity"
    }

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = loginEmailEditText.text.toString()
        val password = loginPasswordEditText.text.toString()

        loginButton.setOnClickListener {
            Log.d(TAG, "Clicked Login Button")
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK.or(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent);
        }

        loginCreateAnAccountTextView.setOnClickListener {
            Log.d(TAG, "Create an Account")
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}