package fakhri.kchaou.maddina.model.repository


import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.auth.LoginFragment
import fakhri.kchaou.maddina.view.auth.SignFragment

class UserRepository<T>(app: T) {

    private val database = Firebase.database
    private val db_reference = database.getReference("users")
    private lateinit var auth: FirebaseAuth




    private var _app = app;


    fun insertTORealTimeDataBase(user: User){

        user.password =""
        val database = Firebase.database
        val refDB = database.getReference("users")

        refDB.child(user.id.toString()).setValue(user)

    }

    fun logout(){
        auth = Firebase.auth
        auth.signOut()
    }

    fun ifLogin() :String? {

        auth = Firebase.auth

        var user = auth.currentUser

        if (user != null) {
            return  user.uid
        }

        return null
    }


    fun add(user: User)  {
        var signFragment = _app as SignFragment

        try {

            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user.id = auth.currentUser?.uid


                        /**********Insert User to realTime database **************/
                        insertTORealTimeDataBase(user)
                        /**********End Insert User to realTime database **********/

                        Toast.makeText(signFragment.context, "تم انشاء الحساب بنجاح",
                            Toast.LENGTH_SHORT).show()

                        signFragment.updateUI(true)
                    } else {
                        val  msg = task.exception.toString()
                        Log.println(Log.ASSERT, ContentValues.TAG, msg.length.toString())
                        if(msg.length.equals(116)){
                            Toast.makeText(signFragment.context,"الحساب موجود بالفعل ", Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(signFragment.context,"حدث خطأ ما, حاول لاحقا", Toast.LENGTH_LONG).show();


                    }
                }
        }
        catch (e:Exception){
            Toast.makeText(signFragment.context,e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun login(email: String, password: String) {
        var loginFragment = _app as LoginFragment

        val sharedPreferences: SharedPreferences? = loginFragment.activity?.getSharedPreferences("user", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        loginFragment.updateUI(false)

        try {
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){
                    /********** GET id of current user ***********************/
                    var id = auth.currentUser?.uid
                    editor?.putString("userId", id)
                    editor?.commit()
                    /********** End GET id of current user *******************/

                    loginFragment.updateUI(true)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(loginFragment.context,"المعلومات التي أدخلتها غير صحيحة", Toast.LENGTH_LONG).show();

            }

        }catch(e :Exception){

            Toast.makeText(loginFragment.context,"حدث خطأ ما, حاول لاحقا", Toast.LENGTH_LONG).show();
        }

    }



}