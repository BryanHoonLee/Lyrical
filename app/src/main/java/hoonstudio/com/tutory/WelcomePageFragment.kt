package hoonstudio.com.tutory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_welcome_page.*

class WelcomePageFragment : Fragment(){

    // if you need to write a function that can be called without having a class instance but needs access to the
    // internals of a class, you can write it as a member of a companion object declaration inside that class
    companion object {

        // provides a Factory Method for creating new instances of WelcomePageFragment
        fun newInstance(): WelcomePageFragment{
            return WelcomePageFragment()
        }
    }

    // Creates the view hierarchy controlled by the fragment
    //
    // Activities use setContentView() to specify the XML file that defines their layouts, but
    // fragments create their view hierarchy in onCreateView()
    //
    // Fragments don't need onCreate() since they are attached to an Activity that calls it
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // LayoutInflater.inflate is used to create the hierarchy for WelcomePageFragment
        // The third parameter of 'inflate' specifies whether the inflated fragment should be added to the container.
        // The container is the parent view that will hold the fragment's view hierarchy
        // This should always be set to false - the FragmentManager will take care of adding the fragment to container
        val view: View = inflater.inflate(R.layout.fragment_welcome_page, container, false)
        val setupAccountNextTextView: TextView = view.findViewById(R.id.setupAccountNextTextView)

        setupAccountNextTextView.setOnClickListener {
            if(savedInstanceState == null){
//                activity?.
//                    supportFragmentManager?.
//                    beginTransaction()?.
//                    replace(R.id.container, WelcomePageProfileFragment.newInstance())?.
//                    addToBackStack(null)?.
//                    commit()
            }
        }
        return view
    }

    // Each activity has a FragmentManager that manages its fragments. It also provides an interface for you to access,
    // add and remove those fragments.
    //
    // WelcomePageFragment has a factory instance method but no constructors because
    // First, i defined no  constructors so the compiler automatically generates an empty, default
    // constructor that takes no arguments. This is all that you should have for a fragment: no other constructors.
    //
    // Second, Android may destroy and later re-create an activity and all its associated
    // fragments when the app goes into the background. When the activity comes back, its FragmentManager starts
    // re-creating fragments by using the empty default constructor. If it cannot find one, you get an exception.
    //
    // For this reason, it is best practice to never specify any non-empty constructors
}