package fakhri.kchaou.maddina.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.view.auth.LoginActivity
import fakhri.kchaou.maddina.view.home.HomeActivity
import fakhri.kchaou.maddina.viewmodel.UserVM

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val userVM = UserVM()

        Handler().postDelayed(Runnable {
            if (userVM.ifLogin() != null){

                val intent = Intent (this, HomeActivity::class.java)
                startActivity(intent)
                this.finish()

            }else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                this.finish()


            }




        }, 2000)

    }
}