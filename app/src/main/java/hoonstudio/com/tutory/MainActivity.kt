package hoonstudio.com.tutory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
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
            R.id.profile -> {
                val profileFragment = ProfileFragment.newInstance()
                startFragment(profileFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun startFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation = bottomNavigation
        bottomNavigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val homeFragment = HomeFragment.newInstance()
        initFragment(homeFragment)
    }

    override fun onBackPressed() {
        var backStackEntryCount = supportFragmentManager.backStackEntryCount
        if (backStackEntryCount == 0) {
            AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes",
                    DialogInterface.OnClickListener { dialog, which -> super@MainActivity.onBackPressed() })
                .setNegativeButton("No", null)
                .show()
        } else {
            super@MainActivity.onBackPressed()
        }

    }
}
