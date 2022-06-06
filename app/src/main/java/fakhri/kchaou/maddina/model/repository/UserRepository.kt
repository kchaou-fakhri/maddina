package fakhri.kchaou.maddina.model.repository


import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.model.repository.remote.UserRemote
import fakhri.kchaou.maddina.utils.Message
import fakhri.kchaou.maddina.view.auth.LoginFragment
import fakhri.kchaou.maddina.view.auth.SignFragment

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


}