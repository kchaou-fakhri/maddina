package fakhri.kchaou.maddina.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.repository.UserRepository

class UserVM<T>(val app: T) : ViewModel() {


    val userRepository = UserRepository(app)


    fun addUser(name : String,email :String, password :String, sex: String) {
        val user = User(name,email, password, sex)
         userRepository.add(user)
    }

    fun login(email :String, password :String ) {

        userRepository.login(email, password)
    }

    fun ifLogin() : String?{
        return userRepository.ifLogin()
    }

    fun logout(){
        userRepository.logout()
    }



}