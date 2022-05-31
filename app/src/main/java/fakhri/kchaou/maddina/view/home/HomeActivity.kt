package fakhri.kchaou.maddina.view.home


import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.view.post.CreatePostFragment
import fakhri.kchaou.maddina.view.profile.UserProfilFragment
import fakhri.kchaou.maddina.viewmodel.UserVM


class HomeActivity : AppCompatActivity() {

    lateinit var createPostFragment: CreatePostFragment
    lateinit var homeFragment : HomeFragment
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


        val bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottom_navigation)

        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_profile))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_create))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_news))

        bottomNavigation.show(3, true)

        createPostFragment = CreatePostFragment()
        homeFragment = HomeFragment()
        userProfilFragment = UserProfilFragment()


        supportFragmentManager.beginTransaction().apply {
            replace(R.id.home_fragment, homeFragment)
            commit()

        }
        bottomNavigation.setOnShowListener {
            when (it.id) {

                2 -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, createPostFragment)
                        commit()

                    }
                }
                3 -> {
                    supportFragmentManager.beginTransaction().apply {

                        replace(R.id.home_fragment, homeFragment)
                        commit()

                    }
                }
                1 -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, userProfilFragment)
                        commit()

                    }
                }
                4 -> {
                    val userVM = UserVM()
                    userVM.logout()
                    this.finish()
                }

            }
        }




        }
    }




