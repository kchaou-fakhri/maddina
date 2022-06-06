package fakhri.kchaou.maddina.view.home


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.view.post.CreatePostActivity
import fakhri.kchaou.maddina.view.profile.UserProfilFragment
import fakhri.kchaou.maddina.viewmodel.UserVM


class HomeActivity : AppCompatActivity() {


    lateinit var homeFragment : HomeFragment
    lateinit var userProfilFragment : UserProfilFragment
    lateinit var bottomNavigation : MeowBottomNavigation
    lateinit var createPostBtn : FloatingActionButton
    companion object{
        var homeActivity: Activity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.getWindow()
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white))
        setContentView(R.layout.activity_home)

        homeActivity = this
        createPostBtn = findViewById(R.id.create_post)
        bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottom_navigation)


        createPostBtn.setOnClickListener{
            val intent = Intent (this, CreatePostActivity::class.java)
            startActivity(intent)


        }

        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_user))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.ic_chat))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_search))

        bottomNavigation.show(1)


        homeFragment = HomeFragment()
        userProfilFragment = UserProfilFragment()



        bottomNavigation.setOnShowListener {
          changeFragment(it.id)

        }
        }




        fun changeFragment(id :Int){


            when (id) {

                1 -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, homeFragment)
                        commit()

                    }
                }

                2 -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, userProfilFragment)
                        commit()
                    }


                }


                3 -> {
                    val userVM = UserVM()
                    userVM.logout()
                    this.finish()
                }

                4 -> {

            }

            }
        }

}




