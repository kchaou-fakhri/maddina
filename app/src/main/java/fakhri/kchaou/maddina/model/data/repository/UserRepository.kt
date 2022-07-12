package fakhri.kchaou.maddina.model.data.repository


import androidx.lifecycle.LiveData
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.data.remote.UserRemote
import fakhri.kchaou.maddina.utils.MessageResult

class UserRepository() {

    var userRemote: UserRemote = UserRemote()


    fun add(user: User) :LiveData<MessageResult> {
        return userRemote.add(user)
    }

    fun login(email: String, password: String) :LiveData<MessageResult>{
       return  userRemote.login(email, password)
    }

    fun ifLogin(): String? {
       return userRemote.ifLogin()
    }

    fun logout() {
        userRemote.logout()
    }

    fun getUserById (id: String): LiveData<User> {
        return userRemote.getUserById(id)
    }

    fun updateUser(user: User): LiveData<MessageResult> {
        return userRemote.updateUser(user)
    }

    fun addFriend(user: User, friend: User): LiveData<MessageResult> {
        return userRemote.addFriend(user, friend)
    }

    fun getFriends(id: String): LiveData<ArrayList<User>> {

        return userRemote.getFrineds(id)
    }

    fun acceptedFriend(user: User, friend: User): LiveData<MessageResult> {
        return userRemote.acceptedFriend(user, friend)
    }

    fun searchByName(query: String): LiveData<ArrayList<User>>  {
        return userRemote.searchByName(query)
    }

    fun getUsers(): LiveData<ArrayList<User>> {
        return userRemote.getUsers()
    }


}