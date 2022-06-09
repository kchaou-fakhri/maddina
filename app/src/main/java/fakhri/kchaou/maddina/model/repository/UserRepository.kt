package fakhri.kchaou.maddina.model.repository


import androidx.lifecycle.LiveData
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.repository.remote.UserRemote
import fakhri.kchaou.maddina.utils.Message

class UserRepository() {

    var userRemote: UserRemote = UserRemote()


    fun add(user: User) :LiveData<Message> {
        return userRemote.add(user)
    }

    fun login(email: String, password: String) :LiveData<Message>{
       return  userRemote.login(email, password)
    }

    fun ifLogin(): String? {
       return userRemote.ifLogin()
    }

    fun logout() {
        userRemote.logout()
    }

    fun getUserById(id: String): LiveData<User> {
        return userRemote.getUserById(id)
    }

    fun updateUser(user: User): LiveData<Message> {
        return userRemote.updateUser(user)
    }

    fun addFriend(user: User, id: String): LiveData<Message> {
        return userRemote.addFriend(user, id)
    }


}