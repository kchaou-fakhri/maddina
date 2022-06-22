package fakhri.kchaou.maddina.view.home


import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentListFriendBinding
import fakhri.kchaou.maddina.view.auth.LoginActivity
import fakhri.kchaou.maddina.view.friend.ListFriendFragment
import fakhri.kchaou.maddina.view.post.CreatePostActivity
import fakhri.kchaou.maddina.view.profile.UserProfilFragment
import fakhri.kchaou.maddina.viewmodel.UserVM


class HomeActivity : AppCompatActivity() {


    lateinit var homeFragment : HomeFragment
    lateinit var userProfilFragment : UserProfilFragment
    lateinit var listFriendFragment: ListFriendFragment
    lateinit var bottomNavigation : MeowBottomNavigation
    lateinit var createPostBtn : FloatingActionButton
    var fragmenSelected : Int = 1

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



        /************** this intent if start home activity from another activity ****************/
        if(intent.extras != null){
                      if(intent.extras!!.getString("fragment").equals("profile")){
                fragmenSelected = 2  // to start profile fragment

            }
        }
        /****************************************************************************************/




        homeActivity = this
        createPostBtn = findViewById(R.id.create_post)
        bottomNavigation = findViewById<MeowBottomNavigation>(R.id.bottom_navigation)


        createPostBtn.setOnClickListener{
            val intent = Intent (this, CreatePostActivity::class.java)
            startActivity(intent)

        }

        bottomNavigation.add(MeowBottomNavigation.Model(1, R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(2, R.drawable.ic_user))
        bottomNavigation.add(MeowBottomNavigation.Model(3, R.drawable.send))
        bottomNavigation.add(MeowBottomNavigation.Model(4, R.drawable.ic_contact))
        bottomNavigation.add(MeowBottomNavigation.Model(5, R.drawable.ic_search))

        bottomNavigation.show(fragmenSelected)


        homeFragment = HomeFragment()
        userProfilFragment = UserProfilFragment()
        listFriendFragment = ListFriendFragment()



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
                    val preferences = getSharedPreferences("user", 0)
                    preferences.edit().remove("userId").commit()
                    val intent = Intent (this, LoginActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }

                4 -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.home_fragment, listFriendFragment)
                        commit()
                    }
            }

                5-> {
            }

            }
        }

}




