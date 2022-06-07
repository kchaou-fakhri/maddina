package fakhri.kchaou.maddina.view.profile.edit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.ActivityEditProfilBinding
import fakhri.kchaou.maddina.view.home.HomeActivity

class EditProfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val window: Window = this.getWindow()
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.white))



        binding.editUserInfo.setOnClickListener {

            val intent = Intent (this, EditUserInfoActivity::class.java)
            startActivity(intent)

        }

    }
}