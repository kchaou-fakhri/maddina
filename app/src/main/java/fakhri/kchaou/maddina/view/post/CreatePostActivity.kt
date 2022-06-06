package fakhri.kchaou.maddina.view.post

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.ActivityCreatePostBinding
import fakhri.kchaou.maddina.view.home.HomeActivity
import fakhri.kchaou.maddina.viewmodel.PostVM
import fakhri.kchaou.maddina.viewmodel.UserVM

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private  var uriImage : Uri? = null
    lateinit var alertDialog : AlertDialog
    lateinit var userVM : UserVM
    lateinit var postVM : PostVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        userVM = UserVM()
        postVM = PostVM()

        binding.btnBack.setOnClickListener   {
            finish()


        }
        binding.cancel.setOnClickListener    {
            binding.postText.setText("")
            binding.postTextLabel.helperText = ""

        }
        binding.btnCreate.setOnClickListener {
            val sharedPreferences: SharedPreferences? = this.getSharedPreferences("user", Context.MODE_PRIVATE)
            val userId :String = sharedPreferences?.getString("userId", "").toString()

            var postText = binding.postText.text.toString()

            if (postText.length >0 || uriImage != null){
                var dialogView = LayoutInflater.from(this).inflate(R.layout.loading_alert, null)
                val builder    = AlertDialog.Builder(this)
                    .setView(dialogView)
                    .setTitle("")
                    .setCancelable(false)
                alertDialog = builder.show()

                userVM.getUserById(userId).observe(this, Observer {
                    var result = postVM.createPost(it, postText, uriImage)
                    result.observe(this, Observer {
                        alertDialog.dismiss()
                        if (it == true){

                            Toast.makeText(this,"تم إضافة قصتك ", Toast.LENGTH_LONG).show();
                            HomeActivity.homeActivity?.finish()
                            Thread.sleep(1000)
                            val intent = Intent (this, HomeActivity::class.java)
                            startActivity(intent)
                            this.finish()

                        }
                        else{

                            Toast.makeText(this,"حدث خطأ ما, حاول لاحقا", Toast.LENGTH_LONG).show();
                        }
                    });
                })

            }else{
                binding.postTextLabel.helperText = "لا يمكن إضافة قصة فارغة"
            }
        }
        binding.addImageViedo.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            this.startActivityForResult(intent,2)

        }


        val  callbacks = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {

                finish()
            }

        }
        this.onBackPressedDispatcher.addCallback(callbacks)



    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            uriImage = data.data

        }
    }

}