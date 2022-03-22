package fakhri.kchaou.maddina
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.entity.User
import fakhri.kchaou.maddina.model.UserModel
import fakhri.kchaou.maddina.viewmodel.UserVM


class LoginActivity : AppCompatActivity() {


    val loginFragment = LoginFragment()
    val signFragment = SignFragment()
    val userVM = UserVM(this)



    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (userVM.ifLogin() != null){

            val intent = Intent (this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()

        }

        else{

            var btn_goToLogin = findViewById<Button>(R.id.go_to_login)
            var btn_goToSign = findViewById<Button>(R.id.go_to_signup)

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.auth_fragment, loginFragment)
                commit()
            }



            btn_goToLogin.setOnClickListener {
                btn_goToLogin.setBackgroundResource(R.color.primary_color)
                btn_goToLogin.setTextColor(getApplication().getResources().getColor(R.color.yellow_color));

                btn_goToSign.setBackgroundResource(R.color.yellow_color)
                btn_goToSign.setTextColor(getApplication().getResources().getColor(R.color.primary_color));

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.auth_fragment, loginFragment)
                    commit()
                }

            }
            btn_goToSign.setOnClickListener {

                btn_goToLogin.setBackgroundResource(R.color.yellow_color)
                btn_goToLogin.setTextColor(R.color.primary_color)

                btn_goToSign.setBackgroundResource(R.color.primary_color)
                btn_goToSign.setTextColor(getApplication().getResources().getColor(R.color.yellow_color));


                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.auth_fragment, signFragment)
                    commit()
                }
            }

        }



        }



}