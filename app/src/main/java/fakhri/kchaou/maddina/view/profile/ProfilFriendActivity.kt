package fakhri.kchaou.maddina.view.profile

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.ActivityProfilFriendBinding
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.viewmodel.UserVM

class ProfilFriendActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfilFriendBinding
    lateinit var userVM : UserVM
    lateinit var user : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilFriendBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userVM = UserVM()

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("userId", "")!!

        val friendId = intent.extras!!.getString("friendId")


        binding.addFriend.setOnClickListener{




                if (checkRelation(user, friendId!!) =="user"){
                    binding.addFriend.setIconResource(R.drawable.ic_access_time)
                    binding.addFriend.text ="لم يتم التحقق"

                }




        }



        userVM.getUserById(id).observe(this, Observer {
            user = it
          for (item in it.relations){
              if (item.id.equals(friendId)){

                   if (item.state == "follower"){
                      binding.addFriend.setIconResource(R.drawable.ic_access_time)
                       binding.addFriend.text ="لم يتم التحقق"

                  }
                  if (item.state == "friend"){
                      binding.addFriend.setIconResource(R.drawable.ic_check_circle)
                      binding.addFriend.text ="أصدقاء"
                  }
                  break
              }

          }
        })


    }


    fun checkRelation(user : User, id: String): String{
        var state = ""
        for (item in user.relations){
            if (item.id.equals(id)){
                state = item.state.toString()
            }
        }
        return state
    }
}