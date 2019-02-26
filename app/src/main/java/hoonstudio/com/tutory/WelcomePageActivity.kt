package hoonstudio.com.tutory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class WelcomePageActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setup_account)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, WelcomePageFragment.newInstance(), "welcomePage")
                .commit()
        }
    }
}

