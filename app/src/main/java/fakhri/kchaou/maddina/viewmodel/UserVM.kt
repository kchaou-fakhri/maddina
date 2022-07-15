package fakhri.kchaou.maddina.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import fakhri.kchaou.maddina.model.entity.Friend
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.data.repository.UserRepository
import fakhri.kchaou.maddina.model.entity.Chat
import fakhri.kchaou.maddina.utils.MessageResult

class UserVM() : ViewModel() {


    lateinit var userRepository : UserRepository

    init {
        userRepository = UserRepository()
    }


    fun addUser(name : String,email :String, password :String , sex: String) : LiveData<MessageResult>{
        val user = User(null,name,email, password, sex, "", "","",ArrayList<Friend>())
        return userRepository.add(user)
    }

    fun login(email :String, password :String ) : LiveData<MessageResult>{

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

    fun updateUser(user: User) : LiveData<MessageResult>{
        return userRepository.updateUser(user)
    }

    fun addFriend(user: User, friend: User) : LiveData<MessageResult>{
        return userRepository.addFriend(user, friend)
    }

    fun getfriends(id: String): LiveData<ArrayList<User>> {
        return userRepository.getFriends(id)
    }

    fun acceptedFriend(user: User, friend: User): LiveData<MessageResult> {
        return userRepository.acceptedFriend(user, friend)
    }

    fun searchByName(query: String) : LiveData<ArrayList<User>> {
        return userRepository.searchByName(query)
    }

    fun getUsers(): LiveData<ArrayList<User>>{
        return userRepository.getUsers()
    }



}