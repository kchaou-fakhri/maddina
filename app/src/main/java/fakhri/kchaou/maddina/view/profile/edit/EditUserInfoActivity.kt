package fakhri.kchaou.maddina.view.profile.edit

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import fakhri.kchaou.maddina.databinding.ActivityEditUserInfoBinding
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.utils.AlertNoConnection
import fakhri.kchaou.maddina.utils.LoadingAlert
import fakhri.kchaou.maddina.viewmodel.UserVM

class EditUserInfoActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditUserInfoBinding
    lateinit var userVM: UserVM
    lateinit var user : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("userId", "")!!
        val alertDialog = LoadingAlert(this)
        alertDialog.startLoadingAlert()

        userVM.getUserById(id).observe(this, Observer {
            alertDialog.dismissDialog()

            if (it != null){
                user = it

                binding.username.setText(user.name)
                binding.job.setText(user.job)
                binding.location.setText(user.adress)
                binding.bio.setText(user.bio)

            }
            else{
                val alertNoConnection = AlertNoConnection(this)
                alertNoConnection.startLoadingAlert()
            }
        })


        binding.btnUpdate.setOnClickListener {

            if (binding.username.text.toString() == ""){

                binding.usernameLabel.helperText = "اسم المستخدم ضروري"
            }
            else
            user.id =id
            user.name = binding.username.text.toString()
            user.job  = binding.job.text.toString()
            user.adress = binding.location.text.toString()
            user.bio = binding.bio.text.toString()
            binding.usernameLabel.helperText = ""

            userVM.updateUser(user).observe(this, Observer {
                Toast.makeText(this,"تم تعديل معلوماتك الشخصية بنجاح", Toast.LENGTH_LONG).show();
            })
        }


    }
}