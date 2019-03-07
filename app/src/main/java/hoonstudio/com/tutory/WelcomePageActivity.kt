package hoonstudio.com.tutory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class WelcomePageActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, WelcomePageFragment.newInstance(), "welcomePage")
                .commit()
        }

    }
}

