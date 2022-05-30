package fakhri.kchaou.maddina.view


import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.view.home.HomeFragment
import fakhri.kchaou.maddina.view.post.CreatePostFragment
import fakhri.kchaou.maddina.view.profile.UserProfilFragment
import fakhri.kchaou.maddina.viewmodel.UserVM


class HomeActivity : AppCompatActivity() {

    lateinit var createPostFragment: CreatePostFragment
    lateinit var profileFragment : HomeFragment
    lateinit var userProfilFragment : UserProfilFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.getWindow()
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        // clear FLAG_TRANSLUCENT_STATUS flag:
        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // finally change the color
        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white))
        setContentView(R.layout.activity_home)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.home
        createPostFragment = CreatePostFragment()
        profileFragment    = HomeFragment()
        userProfilFragment = UserProfilFragment()


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
                R.id.profile -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, userProfilFragment)
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