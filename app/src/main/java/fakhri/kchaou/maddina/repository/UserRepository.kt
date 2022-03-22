package fakhri.kchaou.maddina.repository


import fakhri.kchaou.maddina.SignFragment
import fakhri.kchaou.maddina.entity.User
import fakhri.kchaou.maddina.model.UserModel

class UserRepository<T>(app: T) {
     var userModel = UserModel(app)

    fun addUser(user : User) {
        userModel.add(user)
    }

    fun login(email: String, password: String) {
        userModel.login(email, password)
    }

    fun ifLogin() : String?{
        return userModel.ifLogin()
    }




}