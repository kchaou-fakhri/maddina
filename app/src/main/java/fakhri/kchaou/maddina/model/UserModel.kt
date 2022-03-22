package fakhri.kchaou.maddina.model
import android.content.ContentValues.TAG
import android.util.Log

import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import fakhri.kchaou.maddina.LoginFragment
import fakhri.kchaou.maddina.SignFragment
import fakhri.kchaou.maddina.entity.User


class UserModel<T>(app: T) {

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

        fun ifLogin() :String? {

            auth = Firebase.auth
         //   auth.signOut()
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
                        //Log.println(Log.ASSERT, TAG, Firebase.auth.currentUser.toString())
                        Toast.makeText(signFragment.context, "تم انشاء الحساب بنجاح",
                            Toast.LENGTH_SHORT).show()
                        /**********Insert User to realTime database **************/
                        insertTORealTimeDataBase(user)
                        signFragment.updateUI(true)
                    } else {
                      val  msg = task.exception.toString()
                       Log.println(Log.ASSERT, TAG, msg.length.toString())
                        if(msg.length.equals(116)){
                           Toast.makeText(signFragment.context,"الحساب موجود بالفعل ", Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(signFragment.context,"حدث خطأ ما, حاول لاحقا", Toast.LENGTH_LONG).show();


                    }
                }
        }
        catch (e:Exception){
            Toast.makeText(signFragment.context,e.message,Toast.LENGTH_SHORT).show()
        }
    }

    fun login(email: String, password: String) {
        var loginFragment = _app as LoginFragment

        loginFragment.updateUI(false)

        try {
            auth = Firebase.auth
            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
                if(task.isSuccessful){

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