package fakhri.kchaou.maddina.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.entity.Friend
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.repository.UserRepository
import fakhri.kchaou.maddina.utils.Message

class UserVM() : ViewModel() {


    lateinit var userRepository : UserRepository

    init {
        userRepository = UserRepository()
    }


    fun addUser(name : String,email :String, password :String , sex: String) : LiveData<Message>{
        val user = User(null,name,email, password, sex, "", "","",ArrayList<Friend>())
        return userRepository.add(user)
    }

    fun login(email :String, password :String ) : LiveData<Message>{

      return  userRepository.login(email, password)
    }

    fun ifLogin() : String?{
        return userRepository.ifLogin()
    }

    fun logout(){
        userRepository.logout()
    }

    fun getUserById(id: String) : LiveData<User>{

        return userRepository.getUserById(id)
    }

    fun updateUser(user: User) : LiveData<Message>{
        return userRepository.updateUser(user)
    }

    fun addFriend(user: User, id: String) : LiveData<Message>{
        return userRepository.addFriend(user, id)
    }


}