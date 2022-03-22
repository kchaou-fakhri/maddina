package fakhri.kchaou.maddina


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeActivity : AppCompatActivity() {

    lateinit var createPostFragment: CreatePostFragment
    lateinit var profileFragment : ProfileFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.home
        createPostFragment = CreatePostFragment()
        profileFragment    = ProfileFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment, profileFragment)
            commit()

        }



        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.create_post -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, createPostFragment)
                        commit()

                    }
                  }
                R.id.home -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, profileFragment)
                        commit()

                    }
                }

            }
            true
        }


    }


}