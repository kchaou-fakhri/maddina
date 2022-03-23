package fakhri.kchaou.maddina.view


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.view.post.CreatePostFragment
import fakhri.kchaou.maddina.view.profile.ProfileFragment
import fakhri.kchaou.maddina.viewmodel.UserVM


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
                R.id.menu -> {
                    val userVM = UserVM(this)
                    userVM.logout()
                   this.finish()
                }

            }
            true
        }


    }


}