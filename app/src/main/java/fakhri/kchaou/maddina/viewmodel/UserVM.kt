package fakhri.kchaou.maddina.viewmodel

import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.SignFragment
import fakhri.kchaou.maddina.entity.User
import fakhri.kchaou.maddina.repository.UserRepository

class UserVM<T>(val app: T) : ViewModel() {


    val userRepository = UserRepository(app)


    fun addUser(name : String,email :String, password :String , sex: String) {
        val user = User(null,name,email, password, sex)
         userRepository.addUser(user)
    }

    fun login(email :String, password :String ) {

        userRepository.login(email, password)
    }

    fun ifLogin() : String?{
        return userRepository.ifLogin()
    }



}