package hoonstudio.com.tutory

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import android.content.DialogInterface
import android.os.Build
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import java.io.File


class MainActivity : AppCompatActivity() {

    companion object{
        val CHANNEL_ID = "hoonstudio.com.tutory.ui.MediaPlayerServiceChannel"
    }
//
//    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
//        when (it.itemId) {
//            R.id.favoriteFragment -> {
//                val homeFragment = FavoriteFragment.newInstance()
//                startFragment(homeFragment)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.searchFragment -> {
//                val searchFragment =
//                    SearchFragment.newInstance()
//                startFragment(searchFragment)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.recordingsFragment -> {
//                val recordingsFragment =
//                    RecordingsFragment.newInstance()
//                startFragment(recordingsFragment)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDirectory()
        initNotificationChannel()

        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.playRecordingFragment -> bottomNavigation.visibility = View.GONE
                else -> bottomNavigation.visibility = View.VISIBLE
            }
        }
        val bottomNavigation = bottomNavigation
        bottomNavigation.setupWithNavController(navController)

    }

    private fun initNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Media Player Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            var manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

    private fun initDirectory() {
        val folder = File("${Environment.getExternalStorageDirectory()}${File.separator}Lyrical")
        if (!folder.exists()) {
            folder.mkdir()
        }
    }
//    private fun startFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, fragment)
//            .addToBackStack(null)
//            .commit()
//    }

//    }


    //    private fun initFragment(fragment: Fragment) {
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, fragment)
//            .commit()



//    override fun onBackPressed() {
//        var backStackEntryCount = supportFragmentManager.backStackEntryCount
//        var fragment = supportFragmentManager.fragments.last()
//
//        if (fragment is LyricRecordFragment) {
//            if (fragment.recording) {
//                fragment.initAlertBulder()
//            } else {
//                super@MainActivity.onBackPressed()
//            }
//        } else if (backStackEntryCount == 0) {
//            AlertDialog.Builder(this)
//                .setMessage("Are you sure you want to exit?")
//                .setPositiveButton("Yes",
//                    DialogInterface.OnClickListener { dialog, which -> super@MainActivity.onBackPressed() })
//                .setNegativeButton("No", null)
//                .show()
//        } else {
//            super@MainActivity.onBackPressed()
//        }
//
//    }
}
