package hoonstudio.com.tutory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.home -> {
                val homeFragment = HomeFragment.newInstance()
                startFragment(homeFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.search -> {
                val searchFragment = SearchFragment.newInstance()
                startFragment(searchFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.profile ->{
                val profileFragment = ProfileFragment.newInstance()
                startFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun startFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val homeFragment = HomeFragment.newInstance()
        startFragment(homeFragment)



    }
}
